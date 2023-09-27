//Jacob Landry
import React, { Component } from 'react';
import ConvertisseurEnfant from './ConvertisseurEnfant';
class Convertisseur extends Component {

    state = {
        distance: 0,
        echelle: "cm"
    }

    onDistanceChange = (e, echelleCourante) => {
        this.setState({
            echelle: echelleCourante,
            distance: e.target.value
        })
    }

    handleDistanceCM() {
        switch (this.state.echelle) {
            case "cm":
                return this.state.distance;
            case "m":
                return this.state.distance * 100;
            case "km":
                return this.state.distance * 100000;
            default: return 0;
        }
    }

    handleDistanceM() {
        switch (this.state.echelle) {
            case "cm":
                return this.state.distance / 100;
            case "m":
                return this.state.distance;
            case "km":
                return this.state.distance * 1000;
            default: return 0;
        }
    }

    handleDistanceKM() {
        switch (this.state.echelle) {
            case "cm":
                return this.state.distance / 100000;
            case "m":
                return this.state.distance / 1000;
            case "km":
                return this.state.distance;
            default: return 0;
        }
    }

    render() {
        return (
            <div>
                <h1>Convertisseur</h1>
                <ConvertisseurEnfant texte='Centimètres' echelle="cm" distance={this.handleDistanceCM()} FonctionOnDistanceChange={(e) => { this.onDistanceChange(e, "cm") }} />
                <ConvertisseurEnfant texte='Mètres' echelle="m" distance={this.handleDistanceM()} FonctionOnDistanceChange={(e) => { this.onDistanceChange(e, "m") }} />
                <ConvertisseurEnfant texte='Kilomètres' echelle="km" distance={this.handleDistanceKM()} FonctionOnDistanceChange={(e) => { this.onDistanceChange(e, "km") }} />
            </div>
        );
    }
}
export default Convertisseur;