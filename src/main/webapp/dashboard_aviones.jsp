<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Aviones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link href="estilos_index.css" rel="stylesheet">

    <style>
        .aircraft-management {
            padding: 60px 0;
            background: var(--bg-light);
            min-height: 100vh;
        }

        .management-header {
            background: linear-gradient(135deg, var(--primary-blue), var(--secondary-blue));
            padding: 2rem 0;
            margin-bottom: 3rem;
            border-radius: 15px;
            color: var(--white);
            text-align: center;
            box-shadow: var(--shadow);
        }

        .management-header h2 {
            font-size: 2.5rem;
            font-weight: 700;
            margin-bottom: 0.5rem;
        }

        .management-header p {
            font-size: 1.1rem;
            opacity: 0.9;
            margin: 0;
        }

        .form-card {
            background: var(--white);
            border-radius: 20px;
            padding: 2.5rem;
            box-shadow: var(--shadow);
            margin-bottom: 3rem;
            border: 1px solid rgba(59, 130, 246, 0.1);
        }

        .form-card h4 {
            color: var(--text-dark);
            font-weight: 600;
            margin-bottom: 2rem;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .form-card h4 i {
            color: var(--secondary-blue);
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-label {
            font-weight: 600;
            color: var(--text-dark);
            margin-bottom: 0.5rem;
            display: block;
        }

        .form-control {
            border: 2px solid rgba(59, 130, 246, 0.1);
            border-radius: 10px;
            padding: 12px 15px;
            font-size: 1rem;
            transition: all 0.3s ease;
        }

        .form-control:focus {
            border-color: var(--secondary-blue);
            box-shadow: 0 0 0 0.2rem rgba(59, 130, 246, 0.25);
        }

        .form-select {
            border: 2px solid rgba(59, 130, 246, 0.1);
            border-radius: 10px;
            padding: 12px 15px;
            font-size: 1rem;
            transition: all 0.3s ease;
        }

        .form-select:focus {
            border-color: var(--secondary-blue);
            box-shadow: 0 0 0 0.2rem rgba(59, 130, 246, 0.25);
        }

        .btn-primary-custom {
            background: linear-gradient(135deg, var(--primary-blue), var(--secondary-blue));
            border: none;
            color: var(--white);
            padding: 12px 30px;
            border-radius: 50px;
            font-weight: 600;
            font-size: 1rem;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            box-shadow: 0 4px 15px rgba(59, 130, 246, 0.4);
        }

        .btn-primary-custom:hover {
            background: linear-gradient(135deg, var(--dark-blue), var(--primary-blue));
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(59, 130, 246, 0.5);
            color: var(--white);
        }

        .btn-secondary-custom {
            background: var(--text-light);
            border: none;
            color: var(--white);
            padding: 12px 30px;
            border-radius: 50px;
            font-weight: 600;
            font-size: 1rem;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 8px;
        }

        .btn-secondary-custom:hover {
            background: var(--text-dark);
            color: var(--white);
            transform: translateY(-2px);
        }

        .table-card {
            background: var(--white);
            border-radius: 20px;
            padding: 2rem;
            box-shadow: var(--shadow);
            border: 1px solid rgba(59, 130, 246, 0.1);
        }

        .table-card h4 {
            color: var(--text-dark);
            font-weight: 600;
            margin-bottom: 2rem;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .table-card h4 i {
            color: var(--secondary-blue);
        }

        .table-custom {
            border-collapse: separate;
            border-spacing: 0;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
        }

        .table-custom thead th {
            background: linear-gradient(135deg, var(--primary-blue), var(--secondary-blue));
            color: var(--white);
            font-weight: 600;
            padding: 1.2rem 1rem;
            border: none;
            text-align: center;
            font-size: 0.95rem;
        }

        .table-custom tbody td {
            padding: 1rem;
            border-bottom: 1px solid rgba(0,0,0,0.05);
            vertical-align: middle;
            text-align: center;
        }

        .table-custom tbody tr:hover {
            background-color: rgba(59, 130, 246, 0.05);
        }

        .badge-status {
            padding: 8px 16px;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: 600;
        }

        .badge-activo {
            background-color: #dcfce7;
            color: #166534;
        }

        .badge-inactivo {
            background-color: #fef2f2;
            color: #dc2626;
        }

        .badge-mantenimiento {
            background-color: #fef3c7;
            color: #d97706;
        }

        .btn-action {
            padding: 8px 12px;
            border-radius: 8px;
            border: none;
            font-size: 0.85rem;
            font-weight: 500;
            margin: 0 2px;
            transition: all 0.3s ease;
            cursor: pointer;
        }

        .btn-edit {
            background: #3b82f6;
            color: white;
        }

        .btn-edit:hover {
            background: #1d4ed8;
            transform: translateY(-2px);
        }

        .btn-status {
            background: #10b981;
            color: white;
        }

        .btn-status:hover {
            background: #059669;
            transform: translateY(-2px);
        }

        .btn-delete {
            background: #ef4444;
            color: white;
        }

        .btn-delete:hover {
            background: #dc2626;
            transform: translateY(-2px);
        }

        .empty-state {
            text-align: center;
            padding: 3rem;
            color: var(--text-light);
        }

        .empty-state i {
            font-size: 4rem;
            margin-bottom: 1rem;
            color: rgba(59, 130, 246, 0.3);
        }

        .empty-state h5 {
            font-size: 1.3rem;
            margin-bottom: 0.5rem;
        }

        .fade-in {
            animation: fadeIn 0.5s ease-in;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }
    </style>
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand" href="#">
            <i class="fas fa-plane"></i> AeroManager
        </a>
    </div>
</nav>

<!-- Main Content -->
<div class="aircraft-management">
    <div class="container">
        <!-- Header -->
        <div class="management-header">
            <h2><i class="fas fa-plane-circle-check"></i> Gestión de Aviones</h2>
            <p>Administra tu flota de aeronaves de manera eficiente</p>
        </div>

        <!-- Form Section -->
        <div class="form-card fade-in">
            <h4 id="form-title">
                <i class="fas fa-plus-circle"></i> Registrar Nuevo Avión
            </h4>

            <form id="aircraft-form" action="aviones" method="post">
                <input type="hidden" id="action" name="action" value="crear">
                <input type="hidden" id="id_avion" name="id_avion" value="">

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="modelo" class="form-label">
                                <i class="fas fa-tag"></i> Modelo del Avión
                            </label>
                            <input type="text" class="form-control" id="modelo" name="modelo"
                                   required placeholder="Ej: Boeing 737-800">
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="capacidad" class="form-label">
                                <i class="fas fa-users"></i> Capacidad (Pasajeros)
                            </label>
                            <input type="number" class="form-control" id="capacidad" name="capacidad"
                                   required min="1" max="1000" placeholder="Ej: 180">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="estado_avion" class="form-label">
                                <i class="fas fa-info-circle"></i> Estado del Avión
                            </label>
                            <select class="form-select" id="estado_avion" name="estado_avion" required>
                                <option value="">Seleccionar estado...</option>
                                <option value="Activo">Activo</option>
                                <option value="Inactivo">Inactivo</option>
                                <option value="Mantenimiento">Mantenimiento</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-md-6 d-flex align-items-end">
                        <div class="form-group w-100">
                            <button type="submit" class="btn-primary-custom me-2">
                                <i class="fas fa-save"></i> <span id="btn-text">Guardar Avión</span>
                            </button>
                            <button type="button" class="btn-secondary-custom" onclick="resetForm()">
                                <i class="fas fa-times"></i> Cancelar
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <!-- Table Section -->
        <div class="table-card fade-in">
            <h4>
                <i class="fas fa-list"></i> Lista de Aviones Registrados
            </h4>

            <div class="table-responsive">
                <%
                    // Simulación de datos para el ejemplo
                    // En tu aplicación real, estos datos vendrían del servlet/controller
                    List<Map<String, Object>> aviones = new ArrayList<>();

                    Map<String, Object> avion1 = new HashMap<>();
                    avion1.put("id_avion", 1);
                    avion1.put("modelo", "Boeing 737-800");
                    avion1.put("capacidad", 180);
                    avion1.put("estado_avion", "Activo");
                    aviones.add(avion1);

                    Map<String, Object> avion2 = new HashMap<>();
                    avion2.put("id_avion", 2);
                    avion2.put("modelo", "Airbus A320");
                    avion2.put("capacidad", 150);
                    avion2.put("estado_avion", "Mantenimiento");
                    aviones.add(avion2);

                    Map<String, Object> avion3 = new HashMap<>();
                    avion3.put("id_avion", 3);
                    avion3.put("modelo", "Boeing 777-300");
                    avion3.put("capacidad", 396);
                    avion3.put("estado_avion", "Activo");
                    aviones.add(avion3);

                    // En tu aplicación real, usarías algo como:
                    // List<Avion> aviones = (List<Avion>) request.getAttribute("aviones");
                %>

                <% if (aviones != null && !aviones.isEmpty()) { %>
                <table class="table table-custom">
                    <thead>
                    <tr>
                        <th><i class="fas fa-hashtag"></i> ID</th>
                        <th><i class="fas fa-plane"></i> Modelo</th>
                        <th><i class="fas fa-users"></i> Capacidad</th>
                        <th><i class="fas fa-info-circle"></i> Estado</th>
                        <th><i class="fas fa-cogs"></i> Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Map<String, Object> avion : aviones) { %>
                    <tr>
                        <td><strong>#<%= avion.get("id_avion") %></strong></td>
                        <td><%= avion.get("modelo") %></td>
                        <td><%= avion.get("capacidad") %> pasajeros</td>
                        <td>
                            <%
                                String estado = (String) avion.get("estado_avion");
                                String badgeClass = "";
                                if ("Activo".equals(estado)) {
                                    badgeClass = "badge-activo";
                                } else if ("Inactivo".equals(estado)) {
                                    badgeClass = "badge-inactivo";
                                } else if ("Mantenimiento".equals(estado)) {
                                    badgeClass = "badge-mantenimiento";
                                }
                            %>
                            <span class="badge-status <%= badgeClass %>">
                                            <%= estado %>
                                        </span>
                        </td>
                        <td>
                            <button type="button" class="btn-action btn-edit"
                                    onclick="editAircraft(<%= avion.get("id_avion") %>,
                                            '<%= avion.get("modelo") %>',
                                        <%= avion.get("capacidad") %>,
                                            '<%= avion.get("estado_avion") %>')"
                                    title="Editar">
                                <i class="fas fa-edit"></i>
                            </button>

                            <button type="button" class="btn-action btn-status"
                                    onclick="changeStatus(<%= avion.get("id_avion") %>, '<%= avion.get("estado_avion") %>')"
                                    title="Cambiar Estado">
                                <i class="fas fa-exchange-alt"></i>
                            </button>

                            <button type="button" class="btn-action btn-delete"
                                    onclick="deleteAircraft(<%= avion.get("id_avion") %>, '<%= avion.get("modelo") %>')"
                                    title="Eliminar">
                                <i class="fas fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
                <% } else { %>
                <div class="empty-state">
                    <i class="fas fa-plane-slash"></i>
                    <h5>No hay aviones registrados</h5>
                    <p>Comienza agregando tu primer avión usando el formulario superior.</p>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Variables globales
    let isEditMode = false;

    // Función para editar un avión
    function editAircraft(id, modelo, capacidad, estado) {
        isEditMode = true;

        // Cambiar el título del formulario
        document.getElementById('form-title').innerHTML = '<i class="fas fa-edit"></i> Editar Avión';
        document.getElementById('btn-text').textContent = 'Actualizar Avión';

        // Llenar el formulario con los datos
        document.getElementById('action').value = 'editar';
        document.getElementById('id_avion').value = id;
        document.getElementById('modelo').value = modelo;
        document.getElementById('capacidad').value = capacidad;
        document.getElementById('estado_avion').value = estado;

        // Scroll hacia el formulario
        document.querySelector('.form-card').scrollIntoView({
            behavior: 'smooth'
        });

        // Resaltar el formulario
        const formCard = document.querySelector('.form-card');
        formCard.style.boxShadow = '0 0 30px rgba(59, 130, 246, 0.3)';
        formCard.style.border = '2px solid var(--secondary-blue)';
    }

    // Función para cambiar estado
    function changeStatus(id, currentStatus) {
        const estados = ['Activo', 'Inactivo', 'Mantenimiento'];
        const nextStatus = estados.find(estado => estado !== currentStatus);

        if (confirm(`¿Deseas cambiar el estado del avión a "${nextStatus}"?`)) {
            // Aquí enviarías la petición para cambiar el estado
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = 'aviones';
            form.style.display = 'none';

            const actionInput = document.createElement('input');
            actionInput.type = 'hidden';
            actionInput.name = 'action';
            actionInput.value = 'cambiar_estado';

            const idInput = document.createElement('input');
            idInput.type = 'hidden';
            idInput.name = 'id_avion';
            idInput.value = id;

            const estadoInput = document.createElement('input');
            estadoInput.type = 'hidden';
            estadoInput.name = 'nuevo_estado';
            estadoInput.value = nextStatus;

            form.appendChild(actionInput);
            form.appendChild(idInput);
            form.appendChild(estadoInput);

            document.body.appendChild(form);
            form.submit();
        }
    }

    // Función para eliminar avión
    function deleteAircraft(id, modelo) {
        if (confirm(`¿Estás seguro de que deseas eliminar el avión "${modelo}"?\n\nEsta acción no se puede deshacer.`)) {
            // Aquí enviarías la petición para eliminar
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = 'aviones';
            form.style.display = 'none';

            const actionInput = document.createElement('input');
            actionInput.type = 'hidden';
            actionInput.name = 'action';
            actionInput.value = 'eliminar';

            const idInput = document.createElement('input');
            idInput.type = 'hidden';
            idInput.name = 'id_avion';
            idInput.value = id;

            form.appendChild(actionInput);
            form.appendChild(idInput);

            document.body.appendChild(form);
            form.submit();
        }
    }

    // Función para resetear el formulario
    function resetForm() {
        isEditMode = false;

        // Restaurar título
        document.getElementById('form-title').innerHTML = '<i class="fas fa-plus-circle"></i> Registrar Nuevo Avión';
        document.getElementById('btn-text').textContent = 'Guardar Avión';

        // Limpiar formulario
        document.getElementById('aircraft-form').reset();
        document.getElementById('action').value = 'crear';
        document.getElementById('id_avion').value = '';

        // Restaurar estilos del formulario
        const formCard = document.querySelector('.form-card');
        formCard.style.boxShadow = 'var(--shadow)';
        formCard.style.border = '1px solid rgba(59, 130, 246, 0.1)';
    }

    // Validación del formulario
    document.getElementById('aircraft-form').addEventListener('submit', function(e) {
        const modelo = document.getElementById('modelo').value.trim();
        const capacidad = parseInt(document.getElementById('capacidad').value);
        const estado = document.getElementById('estado_avion').value;

        if (!modelo) {
            alert('Por favor ingresa el modelo del avión');
            e.preventDefault();
            return false;
        }

        if (!capacidad || capacidad < 1 || capacidad > 1000) {
            alert('La capacidad debe ser un número entre 1 y 1000');
            e.preventDefault();
            return false;
        }

        if (!estado) {
            alert('Por favor selecciona un estado para el avión');
            e.preventDefault();
            return false;
        }

        return true;
    });

    // Efecto de aparición suave
    document.addEventListener('DOMContentLoaded', function() {
        const elements = document.querySelectorAll('.fade-in');
        elements.forEach((el, index) => {
            setTimeout(() => {
                el.style.opacity = '1';
                el.style.transform = 'translateY(0)';
            }, index * 200);
        });
    });

    // Inicializar elementos con fade-in
    document.querySelectorAll('.fade-in').forEach(el => {
        el.style.opacity = '0';
        el.style.transform = 'translateY(20px)';
        el.style.transition = 'all 0.5s ease';
    });
</script>
</body>
</html>