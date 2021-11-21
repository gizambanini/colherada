using System.Collections.Generic; 
using System.Linq;
using System;
using Microsoft.AspNetCore.Mvc; 
using Colherada_API.Data; 
using Colherada_API.Models;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace Colherada_API.Controllers
{
   
        [Route("api/[controller]")] 
        [ApiController] 
        public class AvaliacaoController : Controller 
        { 
            private readonly ColheradaContext _context; 
            public AvaliacaoController(ColheradaContext context) 
            {   
                // construtor 
                _context = context; 
            } 

            [HttpGet] 
            public ActionResult<List<Avaliacao>> GetAll() 
            { 
                return _context.Avaliacao.ToList(); 
            } 

            //Método GET / Com o idReceita passado
            [HttpGet ("{idReceita}")]
            public ActionResult<List<AvaliacaoUser>> GetAvaliaçõesByIdReceita(int idReceita) 
            {
                try
                {
                    List<AvaliacaoUser> listaRetorno = new List<AvaliacaoUser>();
                    var avaliacao = _context.Avaliacao.Where( o => o.receita == idReceita).ToList();
                    foreach(Avaliacao a in avaliacao)
                    {
                        var user = _context.Usuarios.Where(o => o.id == a.usuario).First();
                        AvaliacaoUser aUser = new AvaliacaoUser(user.nome, user.foto, a.comentario, a.curtida);
                        listaRetorno.Add(aUser);
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
            public async Task<ActionResult> post(Avaliacao model)
            {
                try
                {
                    Console.WriteLine(model.receita + "/User:" + model.usuario);
                    _context.Avaliacao.Add(model);
                    if (await _context.SaveChangesAsync() == 1)
                    {
                        return Created($"/api/Avaliacao/{model.receita}", model);
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