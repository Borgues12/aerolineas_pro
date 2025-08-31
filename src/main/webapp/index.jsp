<%--
    Document   : index
    Created on : 28/08/2025, 22:05:33
    Author     : DELL
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SkyTravel - Aerolineas</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/estilos_index.css">
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#home">
            <i class="fas fa-plane me-2"></i> SkyTravel
        </a>
        <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <i class="fas fa-bars text-white"></i>
        </button>
        <div class="collapse navbar-collapse" id_pasajero="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#home">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#modulos">Módulos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#flota">Flota</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#nosotros">Nosotros</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#contacto">Contacto</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<section id_pasajero="home" class="hero">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="hero-content">
                    <h1>Aerolineas</h1>
                    <p class="lead">
                        Plataforma integral para la administracion completa de aerolineas.
                        Gestiona vuelos, aviones, pasajeros y reservas desde un solo lugar
                        con la mas alta tecnologia y seguridad del mercado.
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Management Modules Section -->
<section id_pasajero="modulos" class="management-modules">
    <div class="container">
        <div class="section-title">
            <h2>Modulos del Sistema</h2>
            <p class="subtitle">
                Accede a cada modulo de gestion para administrar todos los aspectos
                de tu aerolinea de manera eficiente y profesional.
            </p>
        </div>
        <div class="row g-4">
            <div class="col-lg-3 col-md-6">
                <div class="module-card">
                    <div class="module-icon">
                        <i class="fas fa-route"></i>
                    </div>
                    <h4>Gestion de Vuelos</h4>
                    <p>Administre horarios, rutas, estados de vuelo y toda la informacion relacionada con los vuelos de su aerolinea.</p>
                    <a href="VuelosController?" class="btn-access">
                        Acceder <i class="fas fa-arrow-right"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="module-card">
                    <div class="module-icon">
                        <i class="fas fa-plane"></i>
                    </div>
                    <h4>Gestion de Aviones</h4>
                    <p>Control completo de la flota: mantenimiento, disponibilidad, especificaciones tecnicas y asignacion de aeronaves.</p>
                    <a href="AvionesController?accion=listar" class="btn-access">
                        Acceder <i class="fas fa-arrow-right"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="module-card">
                    <div class="module-icon">
                        <i class="fas fa-users"></i>
                    </div>
                    <h4>Gestion de Pasajeros</h4>
                    <p>Administre perfiles de pasajeros, historial de vuelos, preferencias y toda la informacion de clientes.</p>
                    <a href="PasajerosController?action=pasajeros_page" class="btn-access">
                        Acceder <i class="fas fa-arrow-right"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="module-card">
                    <div class="module-icon">
                        <i class="fas fa-ticket-alt"></i>
                    </div>
                    <h4>Gestion de Reservas</h4>
                    <p>Sistema completo de reservas: creacion, modificacion, cancelacion y seguimiento de todas las reservas.</p>
                    <a href="#" class="btn-access">
                        Acceder <i class="fas fa-arrow-right"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Fleet Gallery Section -->
<section id_pasajero="flota" class="fleet-gallery">
    <div class="container">
        <div class="section-title">
            <h2>Nuestra Flota</h2>
            <p class="subtitle">
                Aeronaves de ultima generacion con los mas altos estandares de seguridad y confort
            </p>
        </div>
        <div class="row g-4">
            <div class="col-lg-4 col-md-6">
                <div class="aircraft-slide">
                    <img src="https://images.unsplash.com/photo-1583987781-2ad6b3c9a7ff?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" alt="Boeing 737">
                    <div class="aircraft-overlay">
                        <h5>Boeing 737-800</h5>
                        <p>Capacidad: 189 pasajeros ? Modelo: B738</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="aircraft-slide">
                    <img src="https://images.unsplash.com/photo-1544451256-4d5e78537c90?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" alt="Airbus A320">
                    <div class="aircraft-overlay">
                        <h5>Airbus A320</h5>
                        <p>Capacidad: 180 pasajeros ? Modelo: A320</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="aircraft-slide">
                    <img src="https://images.unsplash.com/photo-1540962351504-03099e0a754b?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" alt="Boeing 787">
                    <div class="aircraft-overlay">
                        <h5>Boeing 787 Dreamliner</h5>
                        <p>Capacidad: 330 pasajeros ? Modelo: B787</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="aircraft-slide">
                    <img src="https://images.unsplash.com/photo-1569629743817-70c4306b2869?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" alt="Airbus A350">
                    <div class="aircraft-overlay">
                        <h5>Airbus A350</h5>
                        <p>Capacidad: 315 pasajeros ? Modelo: A350</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="aircraft-slide">
                    <img src="https://images.unsplash.com/photo-1556388158-158ea5ccacbd?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" alt="Boeing 777">
                    <div class="aircraft-overlay">
                        <h5>Boeing 777</h5>
                        <p>Capacidad: 396 pasajeros ? Modelo: B777</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="aircraft-slide">
                    <img src="https://images.unsplash.com/photo-1542296332-2e4473faf563?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" alt="Embraer E190">
                    <div class="aircraft-overlay">
                        <h5>Embraer E190</h5>
                        <p>Capacidad: 114 pasajeros ? Modelo: E190</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- About Section -->
<section id_pasajero="nosotros" class="about-section">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6">
                <div class="about-text">
                    <h3>Lideres en Gestion Aeronautica</h3>
                    <p>
                        Con mas de 20 años de experiencia en la industria, SkyTravel se ha consolidado
                        como la plataforma de gestion aeronautica mas confiable del mercado.
                    </p>
                    <p>
                        Nuestro sistema integrado permite a las aerolineas optimizar todas sus operaciones
                        desde una sola plataforma, mejorando la eficiencia y reduciendo costos operativos.
                    </p>
                    <ul class="feature-list">
                        <li><i class="fas fa-check-circle"></i> Tecnologia de vanguardia</li>
                        <li><i class="fas fa-check-circle"></i> Seguridad de datos garantizada</li>
                        <li><i class="fas fa-check-circle"></i> Soporte tecnico 24/7</li>
                        <li><i class="fas fa-check-circle"></i> Integracion con sistemas existentes</li>
                        <li><i class="fas fa-check-circle"></i> Escalabilidad empresarial</li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="about-image">
                    <img src="https://images.unsplash.com/photo-1436491865332-7a61a109cc05?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" alt="Control Tower" class="img-fluid">
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 mb-4">
                <h5><i class="fas fa-plane me-2"></i> SkyTravel</h5>
                <p>
                    Plataforma lider en gestioan aeronutica, ofreciendo soluciones integrales
                    para aerolineas modernas y eficientes.
                </p>
                <div class="social-links">
                    <a href="#"><i class="fab fa-facebook"></i></a>
                    <a href="#"><i class="fab fa-twitter"></i></a>
                    <a href="#"><i class="fab fa-linkedin"></i></a>
                    <a href="#"><i class="fab fa-instagram"></i></a>
                </div>
            </div>
            <div class="col-lg-2 col-md-6 mb-4">
                <h5>Modulos</h5>
                <a href="#">Gestion de Vuelos</a>
                <a href="#">Gestion de Aviones</a>
                <a href="#">Gestion de Pasajeros</a>
                <a href="#">Gestion de Reservas</a>
            </div>
            <div class="col-lg-3 col-md-6 mb-4">
                <h5>Servicios</h5>
                <a href="#">Soporte Tecnico</a>
                <a href="#">Consultorra</a>
                <a href="#">Capacitacion</a>
                <a href="#">Mantenimiento</a>
            </div>
            <div class="col-lg-3 mb-4">
                <h5>Contacto</h5>
                <p><i class="fas fa-map-marker-alt me-2"></i> Quito Ecuador</p>
                <p><i class="fas fa-phone me-2"></i>+593 992 881 814</p>
                <p><i class="fas fa-envelope me-2"></i> anthony.15almache@gmail.com </p>
            </div>
        </div>
        <hr class="my-4" style="border-color: rgba(255,255,255,0.1);">
        <div class="row align-items-center">
            <div class="col-md-6">

            </div>
            <div class="col-md-6 text-md-end">

            </div>
        </div>
    </div>
</footer>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="JS/js_index.js"> </script>