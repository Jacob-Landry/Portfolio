using GameReview.Models;
using Moq;

namespace TestRevueJeu
{
    [TestFixture]
    public class CatalogueModelTest
    {
       
        string nomCatalogue;
        CatalogueModel catalogueUnderTest;
        CatalogueModel emptyCatalogueUnderTest;
        Mock<JeuModel> mockJeu1;
        Mock<JeuModel> mockJeu2;

        [SetUp]
        public void Setup()
        {

            this.nomCatalogue = "Catalogue de Test";
            this.catalogueUnderTest = new CatalogueModel("Catalogue de Test");
            this.emptyCatalogueUnderTest = new CatalogueModel();

            mockJeu1 = new Mock<JeuModel>();
            mockJeu2 = new Mock<JeuModel>();
        }

        [Test]
        public void etantDonneUnNom_lorsquonInitialiseLeConstructeur_alorsCreeUnCatalogueProprement()
        {
            // arrange
            // Voir Setup()

            // act
            // Constructeur appeler dans Setup()

            // assert
            Assert.That(this.catalogueUnderTest, Is.Not.Null);
            Assert.That(this.catalogueUnderTest.NomCatalogue, Is.EqualTo(nomCatalogue));
            Assert.That(this.catalogueUnderTest.ListeDeJeux, Is.Empty);
        }

        [Test]
        public void etandDonneUnCatalogue_lorsquonGetSonNom_alorsLeNomEstRetourne()
        {
            // arrange
            // Voir Setup()

            // act
            string nomRetourne = catalogueUnderTest.NomCatalogue;

            // assert
            Assert.That(nomRetourne, Is.EqualTo("Catalogue de Test"));
        }

        [Test]
        public void etandDonneUnCatalogue_lorsquonSetSonNom_alorsLeNomEstModifie()
        {
            // arrange
            // Voir Setup()
            string nouvNom = "Nouveau Catalogue";

            // act
            catalogueUnderTest.NomCatalogue = nouvNom;

            // assert
            Assert.That(catalogueUnderTest.NomCatalogue, Is.EqualTo(nouvNom));
        }

        [Test]
        public void etandDonneUnCatalogue_lorsquonGetSaListeDeJeux_alorsLaListeDeJeuxEstRetourne()
        {
            // arrange
            // Voir Setup()

            // act
            ICollection<JeuModel> listeDeJeuxRetourne = emptyCatalogueUnderTest.ListeDeJeux;

            // assert
            Assert.That(listeDeJeuxRetourne, Is.EqualTo(new List<JeuModel>()));
        }

        [Test]
        public void etandDonneUnCatalogue_lorsquonGetSaListeDeJeux_alorsLaListeDeJeuxEstModifie()
        {
            // arrange
            // Voir Setup()
            List<JeuModel> nouvListeDeJeux = new List<JeuModel>();
            nouvListeDeJeux.Add(mockJeu1.Object);
            nouvListeDeJeux.Add(mockJeu2.Object);

            // act
            emptyCatalogueUnderTest.ListeDeJeux = nouvListeDeJeux;

            // assert
            Assert.That(emptyCatalogueUnderTest.ListeDeJeux, Is.EqualTo(nouvListeDeJeux));
        }

        [Test]
        public void etantDonneDesJeu_lorsquonAjouteDesJeux_alorsListeDeJeuDevraitAugmenter()
        {
            // arrange
            // Voir Setup()

            // act
            catalogueUnderTest.Ajouter(mockJeu1.Object);
            catalogueUnderTest.Ajouter(mockJeu1.Object);

            // assert
            Assert.That(this.catalogueUnderTest.ListeDeJeux.Count, Is.EqualTo(2));
            Assert.That(this.catalogueUnderTest.ListeDeJeux.First, Is.EqualTo(mockJeu1.Object));
        }

        [Test]
        public void etantDonneUnCatalogue_quandOnLeTransformeEnString_alorsLeFormatDevraitEtreRespecte()
        {
            // arrange
            // Voir Setup()
            catalogueUnderTest.Ajouter(mockJeu1.Object);
            string stringAtendu = "NomCatalogue : Catalogue de Test\nListeDeJeux : \n\tListeDeJeux[0] : \n";

            // act
            string stringRetourne = catalogueUnderTest.ToString();

            // assert
            Assert.That(stringRetourne, Is.EqualTo(stringAtendu));
        }
       
    }
}