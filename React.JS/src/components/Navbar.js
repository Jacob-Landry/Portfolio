import React, { Component } from 'react';
import {NavLink} from 'react-router-dom';
class Navbar extends Component {
    render(){
        return(
            <nav className="navBar">
                <ul>
                    <li><NavLink exact to="/">Accueil</NavLink></li>
                    <li><NavLink to="/PlanteCarousel/">Carousel</NavLink></li>
                    <li><NavLink to="/tableau/">Tableau</NavLink></li>
                    <li><NavLink to="/calendrier/">Calendrier</NavLink></li>
                    <li><NavLink to="/convertisseur/">Convertisseur</NavLink></li>
                    <li><NavLink to="/crypto/">Crypto</NavLink></li>
                    <li><NavLink to="/fibonacci/">Fibonacci</NavLink></li>
                    <li><NavLink to="/fibonacciWebWorker/">Fibonacci Web Worker</NavLink></li>
                </ul>
            </nav>
        );
    }
}
export default Navbar;