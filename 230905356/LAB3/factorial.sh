echo "Number is $1"
count=1
product=1

echo "Factorial of the number is: "
while [ "$count" -le "$1" ]; do
	product=$((product * count))
	count=$((count + 1))
done

echo "$product"
