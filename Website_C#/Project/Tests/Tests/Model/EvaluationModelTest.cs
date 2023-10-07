using RevueJeu.Model.Enums;

namespace TestRevueJeu
{
    public class EvaluationModelTest
    {
        
        EnumCote coteEvaluation;
        string descriptionEvaluation;
        JeuModel JeuTest;
        UtilisateurModel utilisateurTest1;
        UtilisateurModel utilisateurTest2;

        EvaluationModel evaluationUnderTest1;
        EvaluationModel evaluationUnderTest2;

        [SetUp]
        public void Setup()
        {
            coteEvaluation = EnumCote.NEUTRE;
            descriptionEvaluation = "Description test";
            utilisateurTest1 = new UtilisateurModel("test1", "test1");
            utilisateurTest2 = new UtilisateurModel("test2", "test2");

            JeuTest = new JeuModel("nomtest", new DateTime(), 0.00, false, 1, 1, 1);

            evaluationUnderTest1 = new EvaluationModel(coteEvaluation, descriptionEvaluation, utilisateurTest1.Id, 1);
            evaluationUnderTest2 = new EvaluationModel(coteEvaluation, descriptionEvaluation, utilisateurTest1.Id, 1);
            evaluationUnderTest1.Jeu = JeuTest;
        }

        [Test]
        public void etantDonneDesObjets_lorsquonInitialiseLeConstructeur_alorsCreeUneEvaluationProprement()
        {
            // arrange
            // Voir Setup()

            // act
            // Constructeur appeler dans Setup()

            // assert
            Assert.That(evaluationUnderTest1, Is.Not.Null);
            Assert.That(evaluationUnderTest1.Jeu, Is.EqualTo(JeuTest));
            Assert.That(evaluationUnderTest1.Cote, Is.EqualTo(coteEvaluation));
            Assert.That(evaluationUnderTest1.Description, Is.EqualTo(descriptionEvaluation));
            Assert.That(evaluationUnderTest1.AuteurId, Is.EqualTo(utilisateurTest1.Id));
        }

        [Test]
        public void etandDonneUneEvaluation_quandOnGetSonJeu_alorsLeJeuestRetourne()
        {
            // arrange
            // Voir Setup()

            // act
            JeuModel jeuRetourne = evaluationUnderTest1.Jeu;

            // assert
            Assert.That(jeuRetourne, Is.EqualTo(JeuTest));
        }

        [Test]
        public void etandDonneUneEvaluation_quandOnSetSonJeu_alorsLeJeuestModifie()
        {
            // arrange
            // Voir Setup()
            Mock<JeuModel> nouvMockJeu = new Mock<JeuModel>();

            // act
            evaluationUnderTest1.Jeu = nouvMockJeu.Object;

            // assert
            Assert.That(evaluationUnderTest1.Jeu, Is.EqualTo(nouvMockJeu.Object));
        }

        [Test]
        public void etandDonneUneEvaluation_quandOnGetSaCote_alorsSaCoteRetourne()
        {
            // arrange
            // Voir Setup()

            // act
            EnumCote coteRetourne = evaluationUnderTest1.Cote;

            // assert
            Assert.That(coteRetourne, Is.EqualTo(coteEvaluation));
        }

        [Test]
        public void etandDonneUneEvaluation_quandOnSetSaCote_alorsSaCoteModifie()
        {
            // arrange
            // Voir Setup()
            EnumCote nouvelleCote = EnumCote.BIEN;


            // act
            evaluationUnderTest1.Cote = nouvelleCote;

            // assert
            Assert.That(evaluationUnderTest1.Cote, Is.EqualTo(nouvelleCote));
        }

        [Test]
        public void etandDonneUneEvaluation_quandOnGetSaDescription_alorsSaDescriptionRetourne()
        {
            // arrange
            // Voir Setup()

            // act
            string descriptionRetourne = evaluationUnderTest1.Description;

            // assert
            Assert.That(descriptionRetourne, Is.EqualTo(descriptionEvaluation));
        }

        [Test]
        public void etandDonneUneEvaluation_quandOnSetSaDescription_alorsSaDescriptionModifie()
        {
            // arrange
            // Voir Setup()
            string nouvelleDescription = "nouvDescription";


            // act
            evaluationUnderTest1.Description = nouvelleDescription;

            // assert
            Assert.That(evaluationUnderTest1.Description, Is.EqualTo(nouvelleDescription));
        }

        [Test]
        public void etandDonneUneEvaluation_quandOnGetSonAuteur_alorsSonAuteurRetourne()
        {
            // arrange
            // Voir Setup()

            // act
            int identifiantRetourne = evaluationUnderTest1.AuteurId;

            // assert
            Assert.That(identifiantRetourne, Is.EqualTo(utilisateurTest1.Id));
        }

        [Test]
        public void etandDonneUneEvaluation_quandOnSetSonAuteur_alorsSonAuteurModifie()
        {
            // arrange
            // Voir Setup()


            // act
            evaluationUnderTest1.AuteurId = utilisateurTest2.Id;

            // assert
            Assert.That(evaluationUnderTest1.AuteurId, Is.EqualTo(utilisateurTest2.Id));
        }

        [Test]
        public void etantDonne2Evaluations_lorsquonCompare_alorsRetourneVraiSiPareil()
        {
            // arrange
            // Voir Setup()
            evaluationUnderTest2.Jeu = JeuTest;
            bool resultat;

            // act
            // Constructeur appeler dans Setup()
            resultat = evaluationUnderTest1.Equals(evaluationUnderTest2);

            // assert
            Assert.That(resultat, Is.EqualTo(true));
        }

        [Test]
        public void etantDonne2Evaluations_lorsquonCompare_alorsRetourneFauxSiDifferent()
        {
            // arrange
            // Voir Setup()
            bool resultat;

            // act
            // Constructeur appeler dans Setup()
            resultat = evaluationUnderTest1.Equals(evaluationUnderTest2);

            // assert
            Assert.That(resultat, Is.EqualTo(false));
        }

        [Test]
        public void etantDonneUneEvaluation_quandOnLaTransformeEnString_alorsLeFormatDeStringEstRespecte()
        {
            // arrange
            // Voir Setup()
            string resultatAttendu = "Cote : NEUTRE\nDescription : Description test\n";

            // act
            string resultatObtenu = evaluationUnderTest1.ToString();

            // assert
            Assert.That(resultatObtenu, Is.EqualTo(resultatAttendu));
        }
    }
}