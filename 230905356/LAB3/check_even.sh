echo "Number is $1"
number=$1
remainder=$((number % 2))
if [ "$remainder" -eq 0 ]; then
	echo "$number is an even number"
else 
	echo "$number is an odd number"
fi
