# Desarrollo de una aplicación multihilo

Rastreador web/crawler

## Descripción

1. La aplicación lee 2 ficheros:
a. El primero contiene las palabras clave a encontrar en determinadas webs.
b. El segundo contendrá las webs que rastreará la aplicación. Al menos 5.
2. La aplicación abre una pestaña por cada dirección web y lanza un proceso
(concurrente) en el cual se busca cada palabra clave.
3. En cada pestaña hay una barra de progreso donde se ve cuántos elementos “p” de
la web han sido ya procesados en base al total de todos ellos.
4. Debajo de la barra de progreso aparece qué palabra clave se está buscando y cuántas
veces ha aparecido ya. Esta información se irá actualizando en tiempo real.
5. Una vez finalizada la búsqueda, en cada pestaña aparecen las estadísticas de cuántas
veces ha aparecido cada palabra clave en la web correspondiente a dicha pestaña.

## Captura

![Captura de Pantalla](ruta/a/tu/captura-de-pantalla.png)


## Miembros del equipo

RICHARD MARTINEZ
DANIEL FANDOS
SANTIAGO PEREZ
