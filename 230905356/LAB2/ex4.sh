echo "First file is $1"
echo "Second file $2"

cat $1 >> sortedlist.txt
cat $2 >> sortedlist.txt
sort -u -n -o sortedlist.txt sortedlist.txt
cat sortedlist.txt
