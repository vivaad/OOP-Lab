#!/bin/bash

calculate_roots() {
    a=$1
    b=$2
    c=$3

    # Calculate the discriminant
    discriminant=$(echo "$b * $b - 4 * $a * $c" | bc)

    case 1 in
        # If discriminant is positive, two real roots exist
        $(echo "$discriminant > 0" | bc))
            root1=$(echo "scale=2; (-$b + sqrt($discriminant)) / (2 * $a)" | bc -l)
            root2=$(echo "scale=2; (-$b - sqrt($discriminant)) / (2 * $a)" | bc -l)
            echo "Two real roots: $root1 and $root2"
            ;;
        # If discriminant is zero, one real root exists
        $(echo "$discriminant == 0" | bc))
            root=$(echo "scale=2; -$b / (2 * $a)" | bc -l)
            echo "One real root: $root"
            ;;
        # If discriminant is negative, complex roots exist
        *)
            realPart=$(echo "scale=2; -$b / (2 * $a)" | bc -l)
            imaginaryPart=$(echo "scale=2; sqrt(-$discriminant) / (2 * $a)" | bc -l)
            echo "Complex roots: $realPart + ${imaginaryPart}i and $realPart - ${imaginaryPart}i"
            ;;
    esac
}

read -p "Enter coefficient a: " a
read -p "Enter coefficient b: " b
read -p "Enter coefficient c: " c

calculate_roots $a $b $c
