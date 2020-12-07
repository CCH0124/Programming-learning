Compare two arrays and return a new array with any items only found in one of the two given arrays, but not both. In other words, return the symmetric difference of the two arrays.

**Note**
You can return the array with its elements in any order.



```javascript
function diffArray(arr1, arr2) {
  var newArr = [];
  if (arr1.length === 0){
    return newArr.concat(arr2);
  }
  if (arr2.length === 0){
    return newArr.concat(arr1);
  }
  // Same, same; but different.
  arr1.filter( arr => {if(arr2.indexOf(arr) === -1) newArr.push(arr);}).concat(
    arr2.filter(arr => {if(arr1.indexOf(arr) === -1) newArr.push(arr);})
  );
  
  return newArr;
}

diffArray([1, 2, 3, 5], [1, 2, 3, 4, 5]);
```

diffArray([1, 2, 3, 5], [1, 2, 3, 4, 5])should return an array.

["diorite", "andesite", "grass", "dirt", "pink wool", "dead shrub"], ["diorite", "andesite", "grass", "dirt", "dead shrub"]should return ["pink wool"].

["diorite", "andesite", "grass", "dirt", "pink wool", "dead shrub"], ["diorite", "andesite", "grass", "dirt", "dead shrub"]should return an array with one item.

["andesite", "grass", "dirt", "pink wool", "dead shrub"], ["diorite", "andesite", "grass", "dirt", "dead shrub"]should return ["diorite", "pink wool"].

["andesite", "grass", "dirt", "pink wool", "dead shrub"], ["diorite", "andesite", "grass", "dirt", "dead shrub"]should return an array with two items.

["andesite", "grass", "dirt", "dead shrub"], ["andesite", "grass", "dirt", "dead shrub"]should return [].

["andesite", "grass", "dirt", "dead shrub"], ["andesite", "grass", "dirt", "dead shrub"]should return an empty array.

[1, 2, 3, 5], [1, 2, 3, 4, 5]should return [4].

[1, 2, 3, 5], [1, 2, 3, 4, 5]should return an array with one item.

[1, "calf", 3, "piglet"], [1, "calf", 3, 4]should return ["piglet", 4].

[1, "calf", 3, "piglet"], [1, "calf", 3, 4]should return an array with two items.

[], ["snuffleupagus", "cookie monster", "elmo"]should return ["snuffleupagus", "cookie monster", "elmo"].

[], ["snuffleupagus", "cookie monster", "elmo"]should return an array with three items.

[1, "calf", 3, "piglet"], [7, "filly"]should return [1, "calf", 3, "piglet", 7, "filly"].

[1, "calf", 3, "piglet"], [7, "filly"]should return an array with six items.

## javascript 相關方法
- [Array.prototype.includes](https://developer.mozilla.org/zh-TW/docs/Web/JavaScript/Reference/Global_Objects/Array/includes)
- [Array.prototype.filter ](https://developer.mozilla.org/zh-TW/docs/Web/JavaScript/Reference/Global_Objects/Array/filter)
- [Array.prototype.concat ](https://developer.mozilla.org/zh-TW/docs/Web/JavaScript/Reference/Global_Objects/Array/concat)

## 額外解法
[Intermediate Algorithm Scripting: Diff Two Arrays](https://guide.freecodecamp.org/certifications/javascript-algorithms-and-data-structures/intermediate-algorithm-scripting/diff-two-arrays)
