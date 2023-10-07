using GameReview.Models;
using GameReview.Models.Enums;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using RevueJeu.Model.Enums;
using System.Reflection.Metadata;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Newtonsoft.Json;

namespace TP2.Models
{
    public class JsonValueConverter<T> : ValueConverter<T, string>
    {
        public JsonValueConverter() : base(v => JsonConvert.SerializeObject(v),
          v => JsonConvert.DeserializeObject<T>(v))
        {
        }
    }

    public class EnumCollectionJsonValueConverter<T> : ValueConverter<ICollection<T>, string> where T : Enum
    {
        public EnumCollectionJsonValueConverter() : base(
          v => JsonConvert
            .SerializeObject(v.Select(e => e.ToString()).ToList()),
          v => JsonConvert
            .DeserializeObject<ICollection<string>>(v)
            .Select(e => (T)Enum.Parse(typeof(T), e)).ToList())
        {
        }
    }

    public class CollectionValueComparer<T> : ValueComparer<ICollection<T>>
    {
        public CollectionValueComparer() : base((c1, c2) => c1.SequenceEqual(c2),
          c => c.Aggregate(0, (a, v) => HashCode.Combine(a, v.GetHashCode())), c => (ICollection<T>)c.ToHashSet())
        {
        }
    }

    public class TopGameContext : DbContext
    {
        public DbSet<JeuModel> Jeux { get; set; }
        public DbSet<UtilisateurModel> Utilisateurs { get; set; }
        public DbSet<CatalogueModel> Catalogues { get; set; }
        public DbSet<EvaluationModel> Evaluations { get; set; }
        public DbSet<StudioDeveloppementModel> StudioDeveloppements { get; set; }
        public DbSet<EditeurModel> Editeurs { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseLazyLoadingProxies().UseSqlServer(
            @"Server=(localdb)\MSSQLLocalDB;Database=Top_Game;Trusted_Connection=True;"
            );
            base.OnConfiguring(optionsBuilder);
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            //Listes d'enums
            var converterCaracteristiquesJeu = new EnumCollectionJsonValueConverter<EnumCaracteristiqueDeJeu>();
            var comparerCaracteristiquesJeu = new CollectionValueComparer<EnumCaracteristiqueDeJeu>();
            var converterGenresJeu = new EnumCollectionJsonValueConverter<EnumGenreDeJeu>();
            var comparerGenresJeu = new CollectionValueComparer<EnumGenreDeJeu>();
            var converterLangagesJeu = new EnumCollectionJsonValueConverter<EnumLangagesDeJeu>();
            var comparerLangagesJeu = new CollectionValueComparer<EnumLangagesDeJeu>();

            modelBuilder.Entity<JeuModel>()
                .Property(p => p.CaracteristiquesDuJeu)
                .HasConversion(converterCaracteristiquesJeu)
                .Metadata.SetValueComparer(comparerCaracteristiquesJeu);
            modelBuilder.Entity<JeuModel>()
                .Property(p => p.GenresDuJeu)
                .HasConversion(converterGenresJeu)
                .Metadata.SetValueComparer(comparerGenresJeu);
            modelBuilder.Entity<JeuModel>()
                .Property(p => p.LangagesDuJeu)
                .HasConversion(converterLangagesJeu)
                .Metadata.SetValueComparer(comparerLangagesJeu);

            // Auto-increment id when adding
            modelBuilder.Entity<CatalogueModel>()
                .Property(p => p.Id)
                .ValueGeneratedOnAdd();
            modelBuilder.Entity<JeuModel>()
                .Property(p => p.Id)
                .ValueGeneratedOnAdd();
            modelBuilder.Entity<EditeurModel>()
                .Property(p => p.Id)
                .ValueGeneratedOnAdd();
            modelBuilder.Entity<StudioDeveloppementModel>()
                .Property(p => p.Id)
                .ValueGeneratedOnAdd();
            modelBuilder.Entity<EvaluationModel>()
                .Property(p => p.Id)
                .ValueGeneratedOnAdd();
            modelBuilder.Entity<UtilisateurModel>()
                .Property(p => p.Id)
            .ValueGeneratedOnAdd();

            // SetUp relation
            modelBuilder.Entity<CatalogueModel>()
                .HasMany(cm => cm.ListeDeJeux)
                .WithOne(lj => lj.Catalogue)
                .OnDelete(DeleteBehavior.NoAction)
                .HasForeignKey(lj => lj.CatalogueId);
            modelBuilder.Entity<UtilisateurModel>()
                .HasMany(um => um.Favoris)
                .WithMany(f => f.Utilisateur);

            modelBuilder.Entity<UtilisateurModel>()
                .HasMany(um => um.Evaluations)
                .WithOne(e => e.Auteur)
                .OnDelete(DeleteBehavior.NoAction)
                .HasForeignKey(e => e.AuteurId);
            modelBuilder.Entity<JeuModel>()
                .HasMany(jm => jm.ListeDEvaluations)
                .WithOne(le => le.Jeu)
                .OnDelete(DeleteBehavior.NoAction)
                .HasForeignKey(le => le.JeuId);
            modelBuilder.Entity<EditeurModel>()
                .HasMany(em => em.Jeux)
                .WithOne(j => j.Editeur)
                .OnDelete(DeleteBehavior.NoAction)
                .HasForeignKey(j => j.EditeurId);
            modelBuilder.Entity<StudioDeveloppementModel>()
                .HasMany(sdm => sdm.Jeux)
                .WithOne(j => j.StudioDeDeveloppement)
                .OnDelete(DeleteBehavior.NoAction)
                .HasForeignKey(j => j.StudioDeDeveloppementId);



            
            //      Ajout de data
            // Ajout Catalogue
            modelBuilder.Entity<CatalogueModel>().HasData(
                 new CatalogueModel()
                 {
                     Id = 1,
                    NomCatalogue = "MainCatalogue",
                 }
             );

            // Ajout Editeur
            modelBuilder.Entity<EditeurModel>().HasData(
                    new EditeurModel()
                    {
                        Id = 1,
                        Nom = "Annapurna Interactive",
                        Description = "Annapurna Interactive, nom commercial de Annapurna Games, LLC est une filiale d\u2019Annapurna Pictures et un \u00E9diteur de jeux vid\u00E9o."
                    },
                    new EditeurModel()
                    {
                        Id = 2,
                        Nom = "Digital Extreme",
                        Description = "Digital Extremes est un studio de d\u00E9veloppement de jeux vid\u00E9o canadien fond\u00E9 en 1993 par James Schmalz, et bas\u00E9 \u00E0 London en Ontario."
                    },
                    new EditeurModel()
                    {
                        Id = 3,
                        Nom = "Bandai Namco Entertainment",
                        Description = "Bandai Namco Entertainment Inc. est une entreprise japonaise, cr\u00E9\u00E9e le 31 mars 2006, qui \u00E9tablit son domaine d\u0027activit\u00E9 dans " +
                        "le secteur du d\u00E9veloppement et de la commercialisation de jeux vid\u00E9o d\u0027arcade et de salon, ainsi que sur t\u00E9l\u00E9phones portables."
                    },
                    new EditeurModel()
                    {
                        Id = 4,
                        Nom = "Bungie",
                        Description = "Bungie Studios est une soci\u00E9t\u00E9 de d\u00E9veloppement et d\u0027\u00E9dition de jeux vid\u00E9o, fond\u00E9e en 1991 sous le nom Bungie Software Products Corporation."
                    },
                    new EditeurModel()
                    {
                        Id = 5,
                        Nom = "2K",
                        Description = "2K Games est une entreprise sp\u00E9cialis\u00E9e dans l\u0027\u00E9dition de jeu vid\u00E9o. L\u0027entreprise est une filiale de Take-Two Interactive, qui poss\u00E8de \u00E9galement " +
                        "le studio Rockstar Games."
                    },
                    new EditeurModel()
                    {
                        Id = 6,
                        Nom = "Blizzard Entertainment",
                        Description = "Chez Blizzard Entertainment, nous mettons notre passion au service du processus cr\u00E9atif. Chaque jour, c\u2019est guid\u00E9s par nos valeurs que nous" +
                        " continuons \u00E0 mettre au point des exp\u00E9riences ludiques l\u00E9gendaires pour tous nos joueurs."
                    },
                    new EditeurModel()
                    {
                        Id = 7,
                        Nom = "ConcernedApe",
                        Description = "Eric Barone, n\u00E9 le 3 d\u00E9cembre 1987 et \u00E9galement connu sous son pseudonyme ConcernedApe, est un d\u00E9veloppeur de jeux vid\u00E9o, " +
                        "concepteur de jeux vid\u00E9o."
                    },
                    new EditeurModel()
                    {
                        Id = 8,
                        Nom = "Amazon Games",
                        Description = "Amazon Games est une division de la soci\u00E9t\u00E9 de vente en ligne Amazon qui se concentre sur le d\u00E9veloppement de jeux vid\u00E9o."
                    },
                    new EditeurModel()
                    {
                        Id = 9,
                        Nom = "Warner Bros Games",
                        Description = "Warner Bros. Interactive Entertainment est une filiale de Warner Bros. Entertainment sp\u00E9cialis\u00E9e dans l\u0027\u00E9dition," +
                        " la conception et la distribution de jeux vid\u00E9o."
                    },
                    new EditeurModel()
                    {
                        Id = 10,
                        Nom = "Sony Interactive Entertainment",
                        Description = "Recognized as a global leader in interactive and digital entertainment, Sony Interactive Entertainment* (SIE) is responsible for the PlayStation\u00AE brand" +
                        " and family of products and services. PlayStation has delivered innovation to the market since the launch of the original PlayStation in Japan in 1994."
                    }
            );

            // Ajout Studio
            modelBuilder.Entity<StudioDeveloppementModel>().HasData(
                    new StudioDeveloppementModel()
                    {
                        Id = 1,
                        Nom = "BlueTwelve Stdio",
                        Description = "BlueTwelve Studio is a small game development team hailing from Montpellier, Occitanie, southern France."
                    },
                    new StudioDeveloppementModel()
                    {
                        Id = 2,
                        Nom = "Digital Extreme",
                        Description = "Digital Extremes est un studio de d\u00E9veloppement de jeux vid\u00E9o canadien fond\u00E9 en 1993 par James Schmalz, et bas\u00E9 \u00E0 London en Ontario."
                    },
                    new StudioDeveloppementModel()
                    {
                        Id = 3,
                        Nom = "FromSoftware",
                        Description = "Elle est notamment connue pour avoir cr\u00E9\u00E9 les s\u00E9ries Dark Souls et Armored Core, ou encore les jeux Bloodborne, Sekiro: Shadows Die Twice et Elden Ring."
                    },
                    new StudioDeveloppementModel()
                    {
                        Id = 4,
                        Nom = "Bungie",
                        Description = "Bungie Studios est une soci\u00E9t\u00E9 de d\u00E9veloppement et d\u0027\u00E9dition de jeux vid\u00E9o, fond\u00E9e en 1991 sous le nom Bungie Software Products Corporation."
                    },
                    new StudioDeveloppementModel()
                    {
                        Id = 5,
                        Nom = "Gearbox Software",
                        Description = "Gearbox Software est un studio am\u00E9ricain de d\u00E9veloppement de jeux vid\u00E9o fond\u00E9 en 1999 et localis\u00E9 \u00E0 Frisco pr\u00E8s de Dallas."
                    },
                    new StudioDeveloppementModel()
                    {
                        Id = 6,
                        Nom = "Blizzard Team",
                        Description = "Chez Blizzard Entertainment, nous mettons notre passion au service du processus cr\u00E9atif. Chaque jour, c\u2019est guid\u00E9s par nos valeurs que" +
                        " nous continuons \u00E0 mettre au point des exp\u00E9riences ludiques l\u00E9gendaires pour tous nos joueurs."
                    },
                    new StudioDeveloppementModel()
                    {
                        Id = 7,
                        Nom = "ConcernedApe",
                        Description = "Eric Barone, n\u00E9 le 3 d\u00E9cembre 1987 et \u00E9galement connu sous son pseudonyme ConcernedApe, est un d\u00E9veloppeur de jeux vid\u00E9o, concepteur de jeux vid\u00E9o."
                    },
                    new StudioDeveloppementModel()
                    {
                        Id = 8,
                        Nom = "SmileGate",
                        Description = "Smilegate est un d\u00E9veloppeur et \u00E9diteur de jeu vid\u00E9o sud-cor\u00E9en. Il comprend cinq secteurs d\u0027activit\u00E9 principaux : le d\u00E9veloppement de" +
                        " jeux, l\u0027\u00E9dition, la plate-forme, l\u0027investissement et la contribution sociale."
                    },
                    new StudioDeveloppementModel()
                    {
                        Id = 9,
                        Nom = "Avalanche Software",
                        Description = "Avalanche Software est un studio de d\u00E9veloppement de jeux vid\u00E9o fond\u00E9 en octobre 1995 par quatre anciens programmeurs du studio Sculptured Software."
                    },
                    new StudioDeveloppementModel()
                    {
                        Id = 10,
                        Nom = "Santa Monica Studio",
                        Description = "Notre studio est compos\u00E9 d\u0027un groupe de cr\u00E9atifs diversifi\u00E9s, ambitieux et hautement collaboratifs qui se renforcent mutuellement," +
                        " tout en travaillant en \u00E9quipe pour offrir des exp\u00E9riences PlayStation de haute qualit\u00E9 ax\u00E9es sur la narration."
                    }
            );

            // Ajout Utilisateurs
            modelBuilder.Entity<UtilisateurModel>().HasData(
                 new UtilisateurModel()
                 {
                     Id = 1,
                     Login = "Admin",
                     MotDePasse = "Passw0rd!",
                     Role = EnumRoleUtilisateur.ADMINISTRATEUR
                 },
                 new UtilisateurModel()
                 {
                     Id = 2,
                     Login = "ImAUser",
                     MotDePasse = "Passw0rd!",
                     Role = EnumRoleUtilisateur.UTILISATEUR
                 }
             );

            // Ajout Jeux
            modelBuilder.Entity<JeuModel>().HasData(
                 new JeuModel()
                 {
                     Id = 1,
                     NomDuJeu = "Stray",
                     GenresDuJeu = new List<EnumGenreDeJeu>() { EnumGenreDeJeu.EXPLORATION, EnumGenreDeJeu.AVENTURE, EnumGenreDeJeu.HISTOIRE },
                     CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.PVE },
                     LangagesDuJeu = new List<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS },
                     DateDeSortie = new DateTime(2022, 06, 19),
                     Prix = 39.990000000000002,
                     ContientDuContenuSupplementaire = false,
                     StudioDeDeveloppementId = 1,
                     EditeurId = 1,
                     CatalogueId = 1
                 },
                 new JeuModel()
                 {
                     Id = 2,
                     NomDuJeu = "Warframe",
                     GenresDuJeu = new List<EnumGenreDeJeu>() { EnumGenreDeJeu.ACTION, EnumGenreDeJeu.HISTOIRE, EnumGenreDeJeu.COMBAT },
                     CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.COOP, EnumCaracteristiqueDeJeu.PVE },
                     LangagesDuJeu = new List<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS },
                     DateDeSortie = new DateTime(2013, 03, 25),
                     Prix = 0.00,
                     ContientDuContenuSupplementaire = false,
                     StudioDeDeveloppementId = 2,
                     EditeurId = 2,
                     CatalogueId = 1
                 },
                 new JeuModel()
                 {
                     Id = 3,
                     NomDuJeu = "Elden Ring",
                     GenresDuJeu = new List<EnumGenreDeJeu>() { EnumGenreDeJeu.COMBAT, EnumGenreDeJeu.AVENTURE, EnumGenreDeJeu.HISTOIRE },
                     CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.COOP, EnumCaracteristiqueDeJeu.PVP, EnumCaracteristiqueDeJeu.PVE },
                     LangagesDuJeu = new List<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS },
                     DateDeSortie = new DateTime(2022, 02, 25),
                     Prix = 79.989999999999995,
                     ContientDuContenuSupplementaire = false,
                     StudioDeDeveloppementId = 3,
                     EditeurId = 3,
                     CatalogueId = 1
                 },
                 new JeuModel()
                 {
                     Id = 4,
                     NomDuJeu = "Destiny 2",
                     GenresDuJeu = new List<EnumGenreDeJeu>() { EnumGenreDeJeu.ACTION, EnumGenreDeJeu.COMBAT, EnumGenreDeJeu.HISTOIRE },
                     CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.MULTIJOUEUR, EnumCaracteristiqueDeJeu.PVP, EnumCaracteristiqueDeJeu.PVE },
                     LangagesDuJeu = new List<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS },
                     DateDeSortie = new DateTime(2017, 09, 06),
                     Prix = 79.989999999999995,
                     ContientDuContenuSupplementaire = true,
                     StudioDeDeveloppementId = 4,
                     EditeurId = 4,
                     CatalogueId = 1
                 },
                 new JeuModel()
                 {
                     Id = 5,
                     NomDuJeu = "Borderlands 3",
                     GenresDuJeu = new List<EnumGenreDeJeu>() { EnumGenreDeJeu.ACTION, EnumGenreDeJeu.COMBAT, EnumGenreDeJeu.HISTOIRE },
                     CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.COOP, EnumCaracteristiqueDeJeu.PVE },
                     LangagesDuJeu = new List<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS },
                     DateDeSortie = new DateTime(2019, 09, 19),
                     Prix = 79.989999999999995,
                     ContientDuContenuSupplementaire = false,
                     StudioDeDeveloppementId = 5,
                     EditeurId = 5,
                     CatalogueId = 1
                 },
                 new JeuModel()
                 {
                     Id = 6,
                     NomDuJeu = "Diablo 3",
                     GenresDuJeu = new List<EnumGenreDeJeu>() { EnumGenreDeJeu.ACTION, EnumGenreDeJeu.COMBAT },
                     CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.COOP, EnumCaracteristiqueDeJeu.PVE },
                     LangagesDuJeu = new List<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS },
                     DateDeSortie = new DateTime(2012, 05, 12),
                     Prix = 24.989999999999998,
                     ContientDuContenuSupplementaire = true,
                     StudioDeDeveloppementId = 6,
                     EditeurId = 6,
                     CatalogueId = 1
                 },
                 new JeuModel()
                 {
                     Id = 7,
                     NomDuJeu = "Stardew Valley",
                     GenresDuJeu = new List<EnumGenreDeJeu>() { EnumGenreDeJeu.AVENTURE, EnumGenreDeJeu.HISTOIRE, EnumGenreDeJeu.EXPLORATION },
                     CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.COOP },
                     LangagesDuJeu = new List<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS },
                     DateDeSortie = new DateTime(2016, 02, 16),
                     Prix = 16.989999999999998,
                     ContientDuContenuSupplementaire = false,
                     StudioDeDeveloppementId = 7,
                     EditeurId = 7,
                     CatalogueId = 1
                 },
                 new JeuModel()
                 {
                     Id = 8,
                     NomDuJeu = "Lost Ark",
                     GenresDuJeu = new List<EnumGenreDeJeu>() { EnumGenreDeJeu.COMBAT, EnumGenreDeJeu.ACTION, EnumGenreDeJeu.HISTOIRE },
                     CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.MULTIJOUEUR, EnumCaracteristiqueDeJeu.PVP, EnumCaracteristiqueDeJeu.PVE },
                     LangagesDuJeu = new List<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS },
                     DateDeSortie = new DateTime(2022, 02, 11),
                     Prix = 0.00,
                     ContientDuContenuSupplementaire = true,
                     StudioDeDeveloppementId = 8,
                     EditeurId = 8,
                     CatalogueId = 1
                 },
                 new JeuModel()
                 {
                     Id = 9,
                     NomDuJeu = "Hogwarts Legacy",
                     GenresDuJeu = new List<EnumGenreDeJeu>() { EnumGenreDeJeu.AVENTURE, EnumGenreDeJeu.COMBAT, EnumGenreDeJeu.HISTOIRE },
                     CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.PVE },
                     LangagesDuJeu = new List<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS },
                     DateDeSortie = new DateTime(2023, 02, 10),
                     Prix = 79.989999999999995,
                     ContientDuContenuSupplementaire = false,
                     StudioDeDeveloppementId = 9,
                     EditeurId = 9,
                     CatalogueId = 1
                 },
                 new JeuModel()
                 {
                     Id = 10,
                     NomDuJeu = "God of war Ragnarok",
                     GenresDuJeu = new List<EnumGenreDeJeu>() { EnumGenreDeJeu.ACTION, EnumGenreDeJeu.COMBAT, EnumGenreDeJeu.AVENTURE },
                     CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.DIFFICILE, EnumCaracteristiqueDeJeu.PVE },
                     LangagesDuJeu = new List<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS },
                     DateDeSortie = new DateTime(2022, 11, 09),
                     Prix = 89.989999999999995,
                     ContientDuContenuSupplementaire = false,
                     StudioDeDeveloppementId = 10,
                     EditeurId = 10,
                     CatalogueId = 1
                 }
             );

            // Ajout Evaluations
            modelBuilder.Entity<EvaluationModel>().HasData(
                    new EvaluationModel()
                    {
                        Id = 1,
                        Cote = EnumCote.BIEN,
                        Description = "Evaluation de test1",
                        AuteurId = 1,
                        JeuId = 1
                    },
                    new EvaluationModel()
                    {
                        Id = 2,
                        Cote = EnumCote.NEUTRE,
                        Description = "Evaluation de test1",
                        AuteurId = 1,
                        JeuId = 1
                    },
                    new EvaluationModel()
                    {
                        Id = 3,
                        Cote = EnumCote.MAUVAIS,
                        Description = "Evaluation de test2",
                        AuteurId = 2,
                        JeuId = 1,
                    }
            );
            
            
            base.OnModelCreating(modelBuilder);

        }
    }
}
