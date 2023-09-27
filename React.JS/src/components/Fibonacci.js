import React, { Component } from "react";

class Fibonacci extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fibonacciIndice: 0,
      fibonacciNumber: 0,
    };
  }

  onIndiceChange = (e) => {
    this.setState({ fibonacciIndice: parseInt(e.target.value) || 0 });
  };

  recursiveFibonacci = (n) => {
    if (n <= 1) {
      return n;
    } else {
      return this.recursiveFibonacci(n - 1) + this.recursiveFibonacci(n - 2);
    }
  };

  calculateFibonacci = () => {
    const { fibonacciIndice } = this.state;
    const fibonacciNumber = this.recursiveFibonacci(fibonacciIndice);
    this.setState({ fibonacciNumber });
  };

  render() {
    return (
      <div>
        <h1>Calculateur de Fibonacci</h1>
        <p>
          Entrez la position de la suite de Fibonacci que vous voulez calculer :
          <input
            type="number"
            id="inputFibonacci"
            value={this.state.fibonacciIndice}
            onChange={this.onIndiceChange}
          />
          <button id="buttonFibonacci" onClick={this.calculateFibonacci}>
            Calculer
          </button>
        </p>
        <p id="resultatFibonacci">
          Fibonacci({this.state.fibonacciIndice}) = {this.state.fibonacciNumber}
        </p>
      </div>
    );
  }
}

export default Fibonacci;
