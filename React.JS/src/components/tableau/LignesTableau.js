import React, { Component } from 'react';

let data = [
    {
        img : "/images/croustade.jpg",
        description : "Croustade fraise rhubarbe",
        price : 10
    },
    {
        img : "/images/gateau.jpg",
        description : "Gâteau fraise rhubarbe",
        price : 20
    },
    {
        img : "/images/tarte.jpeg",
        description : "Tarte fraise rhubarbe",
        price : 8
    },
    {
        img : "/images/muffin.jpeg",
        description : "Muffin fraise rhubarbe",
        price : 4
    }
];

let tdStyle = {
    width: '200px',
    border: '1px solid black'
};

function LignesTableau() {
    return (
        <tbody>
            <tr>
                <td style={tdStyle}>Image</td>
                <td style={tdStyle}>Nom</td>
                <td style={tdStyle}>Prix</td>
            </tr>
            {data.map((item, index) => (
                <tr key={index}>
                    <td style={tdStyle}><img src={item.img} alt="" width="100%" height="100%"/></td>
                    <td style={tdStyle}>{item.description}</td>
                    <td style={tdStyle}>{item.price}</td>
                </tr>
            ))}
        </tbody>
    );
}

export default LignesTableau;