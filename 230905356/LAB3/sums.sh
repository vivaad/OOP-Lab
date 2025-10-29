
sum=0
for number in "$@"; do

    sum=$((sum + number))
done
echo "The sum is: $sum"
