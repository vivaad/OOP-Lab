n=$1
count=0
current_number=1
echo "The first $n odd numbers are:"
while [ "$count" -lt "$n" ]; do
	echo "$current_number"
	current_number=$((current_number + 2))
	count=$((count + 1))
done
