# TicketFix

Sistema web para la gestión de solicitudes de soporte técnico dentro de una empresa o institución.

## Problema que resuelve
Las solicitudes de soporte llegan por canales dispersos (correo, llamadas, mensajería), por lo que se pierden casos, no hay visibilidad del estado de cada solicitud y no existe un historial consultable de soluciones.

## Objetivo
Centralizar el registro, seguimiento y resolución de tickets de soporte técnico.

## Funcionalidades principales
- Consulta del estado de un ticket (RF-01)
- Registro centralizado de solicitudes (RF-02)
- Historial de tickets resueltos (RF-03)
- Notificación de cambio de estado (RF-04)
- Panel resumen para el administrador (RF-05)

## Arquitectura seleccionada
Arquitectura por capas (presentación, lógica de negocio y datos) combinada con el modelo cliente-servidor. Principio de diseño aplicado: separación de responsabilidades.

## Organización frontend / backend
**Frontend (Interfaz Web):** muestra formularios de registro, consulta de estado, tablero de tickets, notificaciones y el panel del administrador. No contiene reglas de negocio ni accede a la base de datos.

**Backend:**
- TicketController: recibe las peticiones de la interfaz y las dirige a la lógica correspondiente.
- TicketService: aplica las reglas de negocio para registrar, actualizar y consultar tickets.
- AuthService: valida credenciales y controla los permisos por rol (usuario, técnico, administrador).
- NotificacionService: genera y envía avisos cuando cambia el estado de un ticket.
- Base de Datos: almacena usuarios, tickets y notificaciones.

**Comunicación:** el frontend envía las peticiones al TicketController (previa validación de AuthService). El controlador las pasa al TicketService, que consulta o guarda en la base de datos y activa NotificacionService cuando cambia un estado. La respuesta regresa por el mismo camino hasta la interfaz.

## Herramientas utilizadas
GitHub (repositorio y control de versiones), GitHub Projects (tablero Scrumban), GitHub Issues (seguimiento de requisitos y tareas), Visual Paradigm Online (diagramas UML y de componentes).

## Estado actual del proyecto
Etapa de análisis, modelado y diseño completada. Desarrollo del backend en curso (versión 0.2.0).

## Documentación técnica
Ver los documentos del repositorio con requisitos, modelado UML, arquitectura, gestión del proyecto y comunicación.

## Autor
Miguel Enrique Roldán Gómez – Ingeniería de Software I, UAPA
