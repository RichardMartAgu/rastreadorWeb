# Desarrollo de una aplicación multihilo

Rastreador web/crawler

## Descripción

1. La aplicación lee 2 ficheros:
a. El primero contiene las palabras clave a encontrar en determinadas webs.
b. El segundo contendrá las webs que rastreará la aplicación. Al menos 5.
2. La aplicación abre una pestaña por cada dirección web y lanza un proceso
(concurrente) en el cual se busca cada palabra clave.

  -2.2. Se puede limitar el máximo número de pestañas/hilos abiertos.
4. En cada pestaña hay una barra de progreso donde se ve cuántos elementos “p” de
la web han sido ya procesados en base al total de todos ellos.

  -3.3 La barra de progreso se reiniciará con cada nueva palabra clave..
5. Debajo de la barra de progreso aparece qué palabra clave se está buscando y cuántas
veces ha aparecido ya. Esta información se irá actualizando en tiempo real.
6. Una vez finalizada la búsqueda, en cada pestaña aparecen las estadísticas de cuántas
veces ha aparecido cada palabra clave en la web correspondiente a dicha pestaña.

## Captura

![Captura de Pantalla](https://github.com/RichardMartAgu/rastreadorWeb/blob/master/screnshot.jpg)


## Miembros del equipo

RICHARD MARTINEZ

DANIEL FANDOS

SANTIAGO PEREZ
