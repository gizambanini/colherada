using Microsoft.EntityFrameworkCore;
using Colherada_API.Models;

namespace Colherada_API.Data
{
    public class ColheradaContext: DbContext
    {
        public ColheradaContext(DbContextOptions<ColheradaContext> options): base (options) 
        { 

        } 
        public DbSet<Receitas> Receitas {get; set;}
        public DbSet<Avaliacao> Avaliacao {get; set;}
        public DbSet<Filtro> Filtro {get; set;}
        public DbSet<ReceitaFiltro> ReceitaFiltro {get; set;}
        public DbSet<ReceitaSalva> ReceitaSalva {get; set;}
        public DbSet<Usuarios> Usuarios {get; set;}
    }
}