'use strict'

var express = require('express');
var bodyParser = require('body-parser');

var app = express();
var api = require('./routes/favorito');//carga 

app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());//le indicamos que trate todo como json y lo convierta a objeto de js ya tratado
/*
Permite acceder a metodos put y delete desde la aplicacion que consume la api

*/
app.use((req, res, next) =>{
	res.header('Access-Control-Allow-Origin','*');//permitido para cualquiera que quiera usar nuestra api
	res.header('Access-Control-Allow-Header', 'x-API-KEY, Origin, X-Requested-Whith, Content-Type, Acecpt, Access-Control-Request-Method'); //header que puede manadar o que le pueden llegar
	res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, DELETE');//metodos que nos pueden llegar
	res.header('Allow', 'GET, POST, OPTIONS, PUT, DELETE');
	next();
});


app.use('/api', api);//todas las rutas van a empezar por /api
//ARROW FUCTION (FUNCION FLECHA) 
/*
si tengo que pasar un solo parametro no necesita ()
si no tengo que pasar ningun parametro se pone ()
si el cuerpo de la funcion es de un renglon como devolver algo no hacen falta las llaves
*/

module.exports = app;