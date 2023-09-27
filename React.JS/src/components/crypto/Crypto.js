import React, { Component } from "react";
import ModifierCrypto from "./ModifierCrypto";

let data = [
  {
    nom: "Bitcoin",
    prix: 36468.6,
    quantite: 10,
  },
  {
    nom: "Ethereum",
    prix: 2208.83,
    quantite: 10,
  },
  {
    nom: "Tether",
    prix: 1.34,
    quantite: 10,
  },
  {
    nom: "USD Coin",
    prix: 1.34,
    quantite: 10,
  },
  {
    nom: "BNB",
    prix: 291.08,
    quantite: 10,
  },
  {
    nom: "XRP",
    prix: 0.69,
    quantite: 10,
  },
  {
    nom: "Cardano",
    prix: 0.34,
    quantite: 10,
  },
  {
    nom: "Solana",
    prix: 26.7,
    quantite: 10,
  },
  {
    nom: "Polkadot",
    prix: 5.54,
    quantite: 10,
  },
];

let tdStyle = {
  width: "200px",
  border: "1px solid black",
};

let tdStyleHead = {
  width: "200px",
  background: "blue",
};

class Crypto extends Component {
  constructor(props) {
    super(props);
    this.state = {
      modifyIndex: -1,
      inputPrix: 0,
      inputQuantite: 0,
    };
  }

  deplacerLigneModification = (newIndex) => {
    this.setState({ modifyIndex: newIndex });
  };

  modifierData = (index, prix, quantite) => {
    data[index].prix = prix;
    data[index].quantite = quantite;
  };

  render() {
    return (
      <div>
        <h1>Crypto</h1>
        <table id="cryptoTable">
          <tbody>
            <tr>
              <td style={tdStyleHead}>Nom</td>
              <td style={tdStyleHead}>Prix</td>
              <td style={tdStyleHead}>Quantite</td>
              <td style={tdStyleHead}>Total</td>
            </tr>
            {data.map((data, index) => {
              return (
                <>
                  <tr key={index}>
                    <td style={tdStyle}>{data.nom + index}</td>
                    <td style={tdStyle}>{data.prix}</td>
                    <td style={tdStyle}>{data.quantite}</td>
                    <td style={tdStyle}>{data.prix * data.quantite}</td>
                    <td style={tdStyle}>
                      <button
                        onClick={() => {
                          this.deplacerLigneModification(index);
                          this.setState({ inputPrix: data.prix });
                          this.setState({ inputQuantite: data.quantite });
                        }}
                      >
                        Modifier
                      </button>
                    </td>
                  </tr>
                  {this.state.modifyIndex === index && (
                    <ModifierCrypto
                      index={this.state.modifyIndex}
                      deplacerLigneModification={this.deplacerLigneModification}
                      modifierData={this.modifierData}
                      inputPrix={this.state.inputPrix}
                      inputQuantite={this.state.inputQuantite}
                      onPrixChange={(e) =>
                        this.setState({ inputPrix: e.target.value })
                      }
                      onQuantiteChange={(e) =>
                        this.setState({ inputQuantite: e.target.value })
                      }
                    />
                  )}
                </>
              );
            })}
          </tbody>
        </table>
      </div>
    );
  }
}

export default Crypto;
