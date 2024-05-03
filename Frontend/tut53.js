console.log(53);

function greet(name,greettext="Greetings from javaScript"){
    console.log(name+` is a good boy `+  greettext)
}

let name="Sathvik";
let name1="Karthik";
let name2="Nikhil";
let greetText1="I am Vampire";

greet(name,greetText1);
greet(name1,greetText1);
greet(name2);

function sum(a,b,c){
    let d = a+b+c;
    return d;
}

let varSum = sum(12,13,14);
console.log(varSum);