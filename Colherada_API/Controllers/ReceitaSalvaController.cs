using System.Collections.Generic; 
using System.Linq;
using Microsoft.AspNetCore.Mvc; 
using Colherada_API.Data; 
using Colherada_API.Models;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace Colherada_API.Controllers
{
    [Route("api/[controller]")] 
    [ApiController] 
    public class ReceitaSalvaController : Controller 
    { 
        private readonly ColheradaContext _context; 
        public ReceitaSalvaController(ColheradaContext context) 
        {   
            // construtor 
            _context = context; 
        } 

        [HttpGet] 
        public ActionResult<List<ReceitaSalva>> GetAll() 
        { 
            return _context.ReceitaSalva.ToList(); 
        } 

        //Método GET / Com o id passado
        [HttpGet ("{UsuarioId}")]
        public ActionResult<List<Receitas>> GetReceitasSalvas(int UsuarioId) 
        {
            try
            {
                List<Receitas> listaRetorno = new List<Receitas>();
                var listaReceitasSalvas = _context.ReceitaSalva.Where(o => o.user == UsuarioId).ToList();
                foreach(ReceitaSalva rs in listaReceitasSalvas)
                {
                    var essaReceita = _context.Receitas.Where(o => o.id == rs.receitas).First();
                    listaRetorno.Add(essaReceita);
                }
                if (listaRetorno == null)
                {
                    return NotFound();
                }
                    
                return Ok(listaRetorno);
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError, "[ERROR] Falha no acesso ao banco de dados.");
            }
        }

        //Método POST / ADICIONAR 
        [HttpPost]
        public async Task<ActionResult> post(ReceitaSalva model)
        {
            try
            {
                _context.ReceitaSalva.Add(model);
                if (await _context.SaveChangesAsync() == 1)
                {
                    return Created($"/api/ReceitaSalva/{model.id}", model);
                }
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError, "[ERROR] Falha no acesso ao banco de dados.");
            }
            // retorna BadRequest se não conseguiu incluir
            return BadRequest();
        }

        //Método DELETE
        [HttpDelete("{idReceita,UsuarioId}")]
        public async Task<ActionResult> delete(int idReceita, int UsuarioId)
        {
            try
            {
                var receitaSalva = _context.ReceitaSalva.Where(o => o.user == UsuarioId);
                receitaSalva = _context.ReceitaSalva.Where(o => o.receitas == idReceita);
                if (receitaSalva == null)
                {
                    //método do EF
                    return NotFound();
                }
                _context.Remove(receitaSalva);
                await _context.SaveChangesAsync();
                return NoContent();
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,"[ERROR] Falha no acesso ao banco de dados.");
            }
            // retorna BadRequest se não conseguiu deletar
            // return BadRequest();
        }

    }
    
}