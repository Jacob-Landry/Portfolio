import React, { Component } from 'react';
import LignesTableau from './LignesTableau';

let tableStyle = {
    borderCollapse: 'collapse',
    border: '1px solid black',
    margin: 'auto',
    width: '40%',
    marginTop: '50px'
};

class Tableau extends Component {
    render() {
        return (
            <div>
                <table style={tableStyle} >
                        <LignesTableau />
                </table>
            </div>
        );
    }
}
export default Tableau;