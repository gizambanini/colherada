namespace Colherada_API.Models
{
    public class Receitas
    {
        public int id {get;set;}	
        public string nome {get;set;}	
        public string ingredientes {get;set;}	
        public string modoPreparo {get;set;}
        public int calorias {get;set;}
        public string foto {get;set;}	
        public int criador {get;set;}	
        public int avaliacao {get;set;}	
    }
}