echo "String is $1"

string=$1
string_length=${#string}
count=0
while [ "$count" -lt "$((string_length / 2))" ]; do
	front_char="${string:$count:1}"
	rear_char="${string:$((string_length-count-1)):1}"
	if [ "$front_char" != "$rear_char" ]; then 
		echo "String is not a palindrome"
		exit
	fi
	count=$((count + 1 ))
done

echo "Strings is palindrome"

