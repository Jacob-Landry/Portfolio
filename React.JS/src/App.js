import './App.css';
import Navbar from './components/Navbar';
import {BrowserRouter,Route,Routes} from 'react-router-dom';
import Accueil from './components/Accueil.js';
import PlanteCarousel from './components/PlanteCarousel.js';
import Tableau from './components/tableau/Tableau.js';
import Calendrier from './components/Calendrier.js';
import Convertisseur from './components/convertisseur/Convertisseur.js';
import Crypto from './components/crypto/Crypto.js';
import Fibonacci from './components/Fibonacci';
import FibonacciWebWorker from './components/FibonacciWebWorker';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar/>
        <Routes>
          <Route exact path="/" element={<Accueil/>}/>
          <Route path="/PlanteCarousel" element={<PlanteCarousel/>}/>
          <Route path="/tableau" element={<Tableau/>}/>
          <Route path="/calendrier" element={<Calendrier/>}/>
          <Route path="/convertisseur" element={<Convertisseur/>}/>
          <Route path="/crypto" element={<Crypto/>}/>
          <Route path="/fibonacci" element={<Fibonacci/>}/>
          <Route path="/fibonacciWebWorker" element={<FibonacciWebWorker/>}/>
        </Routes>
      </div>
      </BrowserRouter>
  );
}

export default App;

//Développeur ayant travailler sur ce projet. Moi: Jacob Landry et Un coéquipier: Charles Perron
//Lien vers le déployment Vercel: https://tp1-web-jl-cp.vercel.app/
