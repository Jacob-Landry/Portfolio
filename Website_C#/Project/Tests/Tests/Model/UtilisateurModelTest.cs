namespace TestRevueJeu
{
    public class UtilisateurModelTest
    {
        
        string loginUtilisateur;
        string motDePasseUtilisateur;
        string nomUtilisateur;
        string prenomUtilisateur;
        EnumRoleUtilisateur roleUtilisateur;
        UtilisateurModel utilisateurUnderTest;
        Mock<JeuModel> mockJeu1;
        Mock<JeuModel> mockJeu2;
        Mock<EvaluationModel> mockEvaluation1;
        Mock<EvaluationModel> mockEvaluation2;

        [SetUp]
        public void Setup()
        {
            this.loginUtilisateur = "UtilisateurTest";
            this.motDePasseUtilisateur = "MotDePasseTest";
            this.nomUtilisateur = "Test nom";
            this.prenomUtilisateur = "Test prenom";
            this.roleUtilisateur = EnumRoleUtilisateur.UTILISATEUR;
            this.utilisateurUnderTest = new UtilisateurModel("UtilisateurTest", "MotDePasseTest");
 

            mockJeu1 = new Mock<JeuModel>();
            mockJeu2 = new Mock<JeuModel>();

            mockEvaluation1 = new Mock<EvaluationModel>();
            mockEvaluation2 = new Mock<EvaluationModel>();
        }

        [Test]
        public void etantDonneUnUtilisateur_lorsquonInitialiseLeConstructeur_alorsCreeUnUtilisateurProprement()
        {
            // arrange
            // Voir Setup()

            // act
            // Constructeur appeler dans Setup()

            // assert
            Assert.That(this.utilisateurUnderTest, Is.Not.Null);
            Assert.That(this.utilisateurUnderTest.Login, Is.EqualTo(loginUtilisateur));
            Assert.That(this.utilisateurUnderTest.MotDePasse, Is.EqualTo(motDePasseUtilisateur));
            Assert.That(this.utilisateurUnderTest.Role, Is.EqualTo(roleUtilisateur));
            Assert.That(this.utilisateurUnderTest.Favoris, Is.Empty);
            Assert.That(this.utilisateurUnderTest.Evaluations, Is.Empty);
        }

        [Test]
        public void etantDonneDesLogins_lorsquonUtiliseGet_alorsLeLoginEstRetourner()
        {
            // arrange
            // Voir Setup()

            // act
            string nomUtilisateurAComparer = "UtilisateurTest";

            // assert
            Assert.That(this.utilisateurUnderTest.Login, Is.EqualTo(nomUtilisateurAComparer));
        }

        [Test]
        public void etantDonneDesLogins_lorsquonUtiliseSet_alorsLeLoginEstChanger()
        {
            // arrange
            // Voir Setup()

            // act
            utilisateurUnderTest.Login = "LoginSetterTest";

            // assert
            Assert.That(this.utilisateurUnderTest.Login, Is.EqualTo("LoginSetterTest"));
        }

        [Test]
        public void etantDonneDesMotDePasse_lorsquonUtiliseGet_alorsLeMotDePasseEstRetourner()
        {
            // arrange
            // Voir Setup()

            // act
            string motDePasseAComparer = "MotDePasseTest";

            // assert
            Assert.That(this.utilisateurUnderTest.MotDePasse, Is.EqualTo(motDePasseAComparer));
        }

        [Test]
        public void etantDonneDesMotDePasse_lorsquonUtiliseSet_alorsLeMotDePasseEstChanger()
        {
            // arrange
            // Voir Setup()

            // act
            utilisateurUnderTest.MotDePasse = "MotDePasseSetterTest";

            // assert
            Assert.That(this.utilisateurUnderTest.MotDePasse, Is.EqualTo("MotDePasseSetterTest"));
        }


        [Test]
        public void etantDonneDesJeux_lorsquonAjouteDesJeux_alorsListeDeFavorisDevraitAugmenter()
        {
            // arrange
            // Voir Setup()

            // act
            utilisateurUnderTest.AjouterFavori(mockJeu1.Object);
            utilisateurUnderTest.AjouterFavori(mockJeu2.Object);

            // assert
            Assert.That(this.utilisateurUnderTest.Favoris.Count, Is.EqualTo(2));
            Assert.That(this.utilisateurUnderTest.Favoris.First, Is.EqualTo(mockJeu1.Object));
        }

        [Test]
        public void etantDonneDesEvaluations_lorsquonAjouteDesEvaluation_alorsListeDEvaluationDevraitAugmenter()
        {
            // arrange
            // Voir Setup()

            // act
            utilisateurUnderTest.AjouterEvaluation(mockEvaluation1.Object);
            utilisateurUnderTest.AjouterEvaluation(mockEvaluation2.Object);

            // assert
            Assert.That(this.utilisateurUnderTest.Evaluations.Count, Is.EqualTo(2));
            Assert.That(this.utilisateurUnderTest.Evaluations.First, Is.EqualTo(mockEvaluation1.Object));
        }

        [Test]
        public void etandDonneUnUtilisateur_quandOnLeTransformeEnString_alorsLeFormatEstRespecte()
        {
            // arrange
            // Voir Setup()
            string stringAttendu = "uuid : 0\nLogin : UtilisateurTest\nMotDePasse : MotDePasseTest\nRole : UTILISATEUR\nFavoris : []\n";
            utilisateurUnderTest.AjouterFavori(mockJeu1.Object);
            mockEvaluation1.Object.Jeu = mockJeu1.Object;
            utilisateurUnderTest.AjouterEvaluation(mockEvaluation1.Object);

            // act
            string stringObtenu = utilisateurUnderTest.ToString();

            // assert
            Assert.That(stringObtenu, Is.EqualTo(stringAttendu));

        }
        
    }
}