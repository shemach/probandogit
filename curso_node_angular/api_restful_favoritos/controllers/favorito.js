'use sstrict'

var Favorito = require('../models/favorito');



function prueba (req, res) {//comente la anterior para poder compara con una funcion callback flecha
	if(req.params.nombre){
		var nombre = req.params.nombre; //guarda el parametro recibido en la URL en una variable para poder usarlo como queramos 

	}else{
		var nombre = "sin nombre";
	}
	res.status(200).send({
		data:[2,3,4],
		message:"Hola mundo con NodeJS y EXPRESS " + nombre
	});
}



function getFavorito(req, res){
	var favoritoId = req.params.id;

	Favorito.findById(favoritoId, function(err, favorito){ //metodo corto para buscar un favorito por ID
		if(err){
			res.status(500).send({message: 'Error al devolver el marcador'});

		}else{
			if(!favorito){
			res.status(404).send({message: 'No hay marcadores'});

			}else{
				res.status(200).send({favorito});
			}
			
		}	

	});

}



function getFavoritos(req, res){
	Favorito.find({}).sort('-title').exec((err, favoritos) =>{
		if(err){
			res.status(500).send({message: 'Error al devolver los marcadores'});//500 error de servidor
		} else {
			if(!favoritos){
			res.status(404).send({message: 'No hay marcadores'});
			} else {
			res.status(200).send({favoritos});	
			}
		}

	});

}



function saveFavorito(req, res){

	var favorito = new Favorito();//creo un objeto del tipo del modelo de la bd
	
	var params = req.body;
	
	//asigno a cada parte del objeto de la bd las partes de los parametros que me llegaron por body
	favorito.title = params.title;
	favorito.description = params.description;
	favorito.url = params.url;
	
	//metodo que ya nos brinda mongoose
	favorito.save((err, favoritoStored) =>{
		if(err){
			res.status(500).send({message: 'Error al guardar el marcador'});//500 error de servidor
		}else{
			res.status(200).send({favorito: favoritoStored});
		}
		
	});

}



function updateFavorito(req, res){
	var favoritoId = req.params.id;
	var update = req.body;
	
	Favorito.findByIdAndUpdate(favoritoId, update, (err, favoritoUpdate) =>{
		if(err){
			res.status(500).send({message: 'Error al actualizar el marcador'})
		}else{
			res.status(200).send({favorito: favoritoUpdate});
		}
	});	

}



function deleteFavorito(req, res){
	var favoritoId = req.params.id;
	
	Favorito.findById(favoritoId, function(err, favorito){ //metodo corto para buscar un favorito por ID
		if(err){
			res.status(500).send({message: 'Error al devolver el marcador'});

		}else{
			if(!favorito){
			res.status(404).send({message: 'No hay marcadores'});

			}else{
				favorito.remove(err =>{
					if(err){
						res.status(500).send({message: 'Error al borrar'});
					}else{
						res.status(200).send({message: '¡El marcador se ha eliminado!'});
					}
				});
			}
			
		}	

	});	
}



module.exports = { // es un objeto con todas las funciones y termina siendo como una especie de clase
	prueba,
	getFavorito,
	getFavoritos,
	saveFavorito,
	updateFavorito,
	deleteFavorito
}