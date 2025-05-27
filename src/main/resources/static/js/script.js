document.addEventListener('DOMContentLoaded', function() {
                // Get all navigation links
                const navLinks = document.querySelectorAll('.nav-link');

                // Function to remove 'active' class from all links
                function removeActiveClass() {
                    navLinks.forEach(link => {
                        link.classList.remove('active');
                    });
                }

                // Add click event listener to each navigation link
                navLinks.forEach(link => {
                    link.addEventListener('click', function() {
                        removeActiveClass(); // Remove 'active' from all
                        this.classList.add('active'); // Add 'active' to the clicked link
                    });
                });

                // Optional: Set active class based on current URL on page load
                const currentPath = window.location.pathname;
                navLinks.forEach(link => {
                    if (link.getAttribute('href') === currentPath) {
                        link.classList.add('active');
                    }
                });
            });