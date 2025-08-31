// Smooth scrolling
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});

// Navbar background change on scroll
window.addEventListener('scroll', function() {
    const navbar = document.querySelector('.navbar');
    if (window.scrollY > 50) {
        navbar.style.background = 'rgba(30, 64, 175, 0.95)';
        navbar.style.backdropFilter = 'blur(10px)';
    } else {
        navbar.style.background = 'linear-gradient(135deg, var(--primary-blue) 0%, var(--secondary-blue) 100%)';
    }
});

// Add loading animation to buttons
document.querySelectorAll('.btn-access').forEach(btn => {
    btn.addEventListener('click', function() {
        const originalText = this.innerHTML;
        this.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i> Cargando...';
        this.style.pointerEvents = 'none';

        setTimeout(() => {
            this.innerHTML = originalText;
            this.style.pointerEvents = 'auto';
        }, 2000);
    });
});

// Counter animation for statistics
function animateCounters() {
    const counters = document.querySelectorAll('.stat-number');
    const speed = 200;

    counters.forEach(counter => {
        const animate = () => {
            const value = +counter.getAttribute('data-target') || +counter.innerText.replace(/[^\d]/g, '');
            const data = +counter.innerText.replace(/[^\d]/g, '') || 0;

            const time = value / speed;
            if (data < value) {
                counter.innerText = Math.ceil(data + time) + (counter.innerText.includes('+') ? '+' : '') + (counter.innerText.includes('%') ? '%' : '') + (counter.innerText.includes('M') ? 'M' : '');
                setTimeout(animate, 1);
            } else {
                counter.innerText = counter.getAttribute('data-original') || counter.innerText;
            }
        };
        animate();
    });
}

// Initialize counter animation when stats section comes into view
const statsSection = document.querySelector('.stats-section');
const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            animateCounters();
            observer.unobserve(entry.target);
        }
    });
});

if (statsSection) {
    observer.observe(statsSection);
}

// Add hover effects and animations
document.querySelectorAll('.module-card').forEach(card => {
    card.addEventListener('mouseenter', function() {
        this.style.transform = 'translateY(-15px) scale(1.02)';
    });

    card.addEventListener('mouseleave', function() {
        this.style.transform = 'translateY(0) scale(1)';
    });
});

// Initialize page
document.addEventListener('DOMContentLoaded', function() {
    // Add fade-in animation to cards
    const cards = document.querySelectorAll('.module-card');
    cards.forEach((card, index) => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(50px)';

        setTimeout(() => {
            card.style.transition = 'all 0.6s ease';
            card.style.opacity = '1';
            card.style.transform = 'translateY(0)';
        }, index * 200);
    });
});