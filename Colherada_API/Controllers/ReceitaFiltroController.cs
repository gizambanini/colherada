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
        public class ReceitaFiltroController : Controller 
        { 
            private readonly ColheradaContext _context; 
            public ReceitaFiltroController(ColheradaContext context) 
            {   
                // construtor 
                _context = context; 
            } 

            [HttpGet] 
            public ActionResult<List<ReceitaFiltro>> GetAll() 
            { 
                return _context.ReceitaFiltro.ToList(); 
            } 

            //Método GET / Com o nome do filtro
            [HttpGet ("{filtro}")]
            public ActionResult<List<Receitas>> GetReceitaByFiltro(string filtro) 
            {
                try
                {
                    List<Receitas> listaRetorno = new List<Receitas>();
                    var esseFiltro = _context.Filtro.Where( o => o.nome == filtro).First();
                    var result = _context.ReceitaFiltro.Where(o => o.filtro == esseFiltro.id).ToList();
                    foreach(ReceitaFiltro rf in result)
                    {
                        var essaReceita = _context.Receitas.Where(o => o.id == rf.receita).First();
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
        }
   
}