We'll pass you an array of two numbers. Return the sum of those two numbers plus the sum of all the numbers between them.

The lowest number will not always come first.

```javascript
function sumAll(arr) {
  var sum = 0;
  for (var i = Math.min(...arr); i <= Math.max(...arr); i++) {
    sum += i;
  }
  return sum;
}

sumAll([1, 4]);
```

test：
sumAll([1, 4])should return a number.

sumAll([1, 4])should return 10.

sumAll([4, 1])should return 10.

sumAll([5, 10])should return 45.

sumAll([10, 5])should return 45.
