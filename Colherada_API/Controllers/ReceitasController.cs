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
    public class ReceitasController : Controller 
    { 
        private readonly ColheradaContext _context; 
        public ReceitasController(ColheradaContext context) 
        {   
            // construtor 
            _context = context; 
        } 

        [HttpGet] 
        public ActionResult<List<Receitas>> GetAll() 
        { 
            return _context.Receitas.ToList(); 
        } 

        //Método GET / Com o id passado
        [HttpGet ("{idReceita}")]
        public ActionResult<List<Receitas>> GetReceita(int idReceita) 
        {
            try
            {
                var result = _context.Receitas.Find(idReceita);
                if (result == null)
                {
                    return NotFound();
                }
                
                return Ok(result);
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError, "[ERROR] Falha no acesso ao banco de dados.");
            }
        }

        //Método POST / ADICIONAR 
        [HttpPost]
        public async Task<ActionResult> post(Receitas model)
        {
            try
            {
                _context.Receitas.Add(model);
                if (await _context.SaveChangesAsync() == 1)
                {
                    return Created($"/api/Receitas/{model.id}", model);
                }
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError, "[ERROR] Falha no acesso ao banco de dados.");
            }
            // retorna BadRequest se não conseguiu incluir
            return BadRequest();
        }
    }
   
}