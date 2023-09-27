import React, { useState } from "react";

let tdStyle = {
  width: "200px",
  border: "1px solid black",
};

function ModifierCrypto(props) {
  return (
    <tr>
      <td style={tdStyle}>Modifier</td>
      <td style={tdStyle}>
        <input value={props.inputPrix} onChange={props.onPrixChange} />
      </td>
      <td style={tdStyle}>
        <input value={props.inputQuantite} onChange={props.onQuantiteChange} />
      </td>
      <td style={tdStyle}>
        <button
          onClick={() => {
            props.modifierData(
              props.index,
              props.inputPrix,
              props.inputQuantite
            );
            props.deplacerLigneModification(-1);
          }}
        >
          Enregistrer
        </button>
      </td>
    </tr>
  );
}

export default ModifierCrypto;
