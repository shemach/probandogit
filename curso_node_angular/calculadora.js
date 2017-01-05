//como usamos ES5 y ES6
'use strict' //hace que traduzca y los navegadores reconozcan los ecma script nuevas como la 6

var args = process.argv.slice(2);//recibe los parametros y los toma como argumento del archivito js

var operation = (args[1]);
var num1 = parseFloat(args[0]);
var num2 = parseFloat(args[2]);

var result = '\n Introduce bien los parámetros \n';

if(args.length == 3){
	switch (operation){
		case "mas":
			result = 'suma: '+parseFloat(num1 + num2);
			break;

		case "menos":
			result = 'resta: '+parseFloat(num1 - num2);
			break;

		case "por":
			result = 'multiplicacion: '+parseFloat(num1 * num2);
			break;

		case "dividido":
			result = 'division: '+parseFloat(num1 / num2);
			break; 
	}
}

console.log(result);