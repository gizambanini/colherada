namespace Colherada_API.Models
{
    public class AvaliacaoUser
    {
        public AvaliacaoUser(string nomeUser, string imagemUser, string comentario, int estrelas)
        {
            this.nomeUser = nomeUser;
            this.imagemUser = imagemUser;
            this.comentario = comentario;
            this.estrelas = estrelas;
        }

        public string nomeUser {get;set;}
        public string imagemUser {get;set;}
        public string comentario {get;set;}
        public int estrelas {get;set;}
    }
}