//A.)
function largest_loop(list) {
    if (list.length < 1) return null;

    var largest = list[0];
    for (const ele of list)
        if (ele > largest)
            largest = ele;

    return largest;
}

console.log("largest_loop : " + largest_loop( [3,2,6,4,9,7] ));


//B.)
function largest_forEach(list) {
    if (list.length < 1) return null;

    var largest = list[0];
    list.forEach( (ele) => {
        if (ele > largest)
            largest = ele;
    });

    return largest;
}

console.log("largest_forEach : " + largest_forEach( [3,2,6,4,9,7] ));

//C.)
function largest_reduce(list) {
    if (list.length < 1) return null;

    return list.reduce( (largest, curVal) => curVal > largest ? curVal : largest, list[0]);
}

console.log("largest_reduce : " +  largest_reduce( [3,2,6,4,9,7] ) );

//D.)
function countingWords(list) {
    const count = {};

    for (const ele of list) {
        let key = String(ele).trim().toLocaleLowerCase();
        count[key] = count[key] ? count[key] + 1 : 1;
    }

    return count;
}

let x = countingWords(['hi', 'hi', 'hello', 'world', 'hello', 'hi', 'greetings', 'World']);
for (const key in x)
    console.log(key + " : " + x[key]);


//E.)
function getTotalSumArray(arr) {
    return arr.reduce( (total, curVal) => isNaN(curVal) ? total : total + Number(curVal) , 0);
}
console.log("Sum : " + getTotalSumArray([5, 2, 'a', 4, '7', true, 'b', 'c', 7, '8', false]) );