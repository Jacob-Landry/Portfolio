//WorkerFibonacci.js

// eslint-disable-next-line import/no-anonymous-default-export
export default () => {
  // eslint-disable-next-line no-restricted-globals
  self.onmessage = (message) => {
    const nbr = message.data;

    function recursiveFibonacci(nbr) {
      if (nbr <= 1) {
        return nbr;
      } else {
        return recursiveFibonacci(nbr - 1) + recursiveFibonacci(nbr - 2);
      }
    }

    postMessage(recursiveFibonacci(nbr));
  };
};
