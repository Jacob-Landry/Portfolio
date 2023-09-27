//Jacob Landry
import React from 'react';
class ConvertisseurEnfant extends React.Component {
    
    render() {
        return (
            <fieldset>
                <legend>Saisissez la mesure en {this.props.texte}</legend>
                <input type='number' onChange={this.props.FonctionOnDistanceChange} value={this.props.distance}></input>
            </fieldset>
        );
    }
}
export default ConvertisseurEnfant;