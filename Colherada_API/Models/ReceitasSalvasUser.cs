using System.ComponentModel.DataAnnotations;

namespace Colherada_API.Models
{
    public class ReceitasSalvasUser
    {
        public ReceitasSalvasUser(string nomeReceita, string imagemReceita)
        {
            this.nomeReceita = nomeReceita;
            this.imagemReceita = imagemReceita;
        }

        public string nomeReceita {get;set;}
        public string imagemReceita {get;set;}
    }
}