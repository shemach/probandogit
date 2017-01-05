'use strict'

var express = require('express');
var FavoritoController = require('../controllers/favorito'); //carga la exportacion que tenga y se puede usar desde aca todas las funcionalidades que tiene el controller
var api = express.Router(); //carga el router de express

//app.get('/prueba/:nombre?/', function(req, res){// con el ? hago que el parametro sea opcional
api.get('/prueba/:nombre?/', FavoritoController.prueba);
api.get('/favoritos', FavoritoController.getFavoritos);
api.get('/favorito/:id', FavoritoController.getFavorito);
api.post('/favorito', FavoritoController.saveFavorito);
api.put('/favorito/:id', FavoritoController.updateFavorito);
api.delete('/favorito/:id', FavoritoController.deleteFavorito);

module.exports = api;