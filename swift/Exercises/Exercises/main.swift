//
//  main.swift
//  Exercises
//
//  Created by Fernando batista on 10/04/23.
//

import Foundation

func printMessage() {
    print("Hello world!!!")
}

func calcAverage(numbers: [Double]) -> Double {
    var sum = 0.0
    for number in numbers {
        sum += number
    }
    let average = sum / Double(numbers.count)
    return average
}

printMessage()
let numbersToAverage = [1.0, 2.0, 3.0, 3.5]
let average = calcAverage(numbers: numbersToAverage)
print(average)

