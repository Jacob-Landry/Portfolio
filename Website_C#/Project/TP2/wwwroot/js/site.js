document.addEventListener('DOMContentLoaded', () => {
    const disconnect = document.querySelector('.disconnect-link');

    disconnect.addEventListener('click', async (event) => {
        event.preventDefault();
        event.stopPropagation(); // stops the event propagation

        // Clears the session
        const response = await fetch('/Landing/Logout', {method: 'POST' });

        if (response.ok) {
            window.location.href = `${window.location.origin}/Landing/Index`;
        } else {
            console.error('Logout failed');
        }
    });
});