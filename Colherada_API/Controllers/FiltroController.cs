using System.Collections.Generic; 
using System.Linq;
using Microsoft.AspNetCore.Mvc; 
using Colherada_API.Data; 
using Colherada_API.Models;

namespace Colherada_API.Controllers
{
   
        [Route("api/[controller]")] 
        [ApiController] 
        public class FiltroController : Controller 
        { 
            private readonly ColheradaContext _context; 
            public FiltroController(ColheradaContext context) 
            {   
                // construtor 
                _context = context; 
            } 

            [HttpGet] 
            public ActionResult<List<Filtro>> GetAll() 
            { 
                return _context.Filtro.ToList(); 
            } 
        }
    
}