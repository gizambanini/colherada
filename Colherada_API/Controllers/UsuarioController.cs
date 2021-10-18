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
    public class UsuarioController : Controller 
    { 
        private readonly ColheradaContext _context; 
        public UsuarioController(ColheradaContext context) 
        {   
            // construtor 
            _context = context; 
        } 

        //Método GET / Pega todos
        [HttpGet] 
        public ActionResult<List<Usuarios>> GetAll() 
        { 
            return _context.Usuarios.ToList(); 
        } 

        //Método GET / Com o email passado / LOGIN
        [HttpGet ("{UsuarioEmail}")]
        public ActionResult<List<Usuarios>> GetEmail(string UsuarioEmail) 
        {
            try
            {
                var result = _context.Usuarios.Where( o => o.email == UsuarioEmail).First();
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
        public async Task<ActionResult> post(Usuarios model)
        {
            try
            {
                _context.Usuarios.Add(model);
                if (await _context.SaveChangesAsync() == 1)
                {
                    return Created($"/api/Usuarios/{model.email}", model);
                }
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError, "[ERROR] Falha no acesso ao banco de dados.");
            }
            // retorna BadRequest se não conseguiu incluir
            return BadRequest();
        }

        //Método PUT / ATUALIZAÇÃO
        [HttpPut("{UsuarioId}")]
        public async Task<IActionResult> put(int UsuarioId, Usuarios dadosUserAlt)
        {
            try
            {
                //verifica se existe usuario a ser alterado
                var result = _context.Usuarios.Where( o => o.id == UsuarioId).First();
                if (UsuarioId != result.id)
                {
                    return BadRequest();
                }
                result.nome = dadosUserAlt.nome;
                result.email = dadosUserAlt.email;
                result.senha = dadosUserAlt.senha;
                result.foto = dadosUserAlt.foto;
                await _context.SaveChangesAsync();
                return Created($"/api/Usuarios/{dadosUserAlt.email}", dadosUserAlt);
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError, "[ERROR] Falha no acesso ao banco de dados.");
            }
        }

        //Método DELETE
        [HttpDelete("{UsuarioId}")]
        public async Task<ActionResult> delete(int UsuarioId)
        {
            try
            {
                //verifica se existe user a ser excluído
                var user = _context.Usuarios.Where( o => o.id == UsuarioId).First();
                if (user == null)
                {
                    //método do EF
                    return NotFound();
                }
                _context.Remove(user);
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