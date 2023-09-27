import React, { Component } from "react";
import WorkerBuilder from "../workers/WorkerBuilder";
import fibonacciWorker from "../workers/WorkerFibonacci";

const worker = new WorkerBuilder(fibonacciWorker);

class FibonacciWebWorker extends Component {
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

  calculateFibonacci = () => {
    const { fibonacciIndice } = this.state;
    worker.postMessage(fibonacciIndice);
    worker.onmessage = (message) => {
      this.setState({ fibonacciNumber: message.data });
    };
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

export default FibonacciWebWorker;
