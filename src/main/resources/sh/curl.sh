echo ""

echo "Creating 'Super Mario Odyssey' product"
curl -X POST \
  http://localhost:8080/pseg/products \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 8649d857-e330-4ebc-823e-f4d04c508ce0' \
  -H 'cache-control: no-cache' \
  -d '{
	"description": "Super Mario Odyssey",
	"price": 249.00
}'
echo ""
read -p "Press enter to continue"

echo ""

echo "Editing 'Super Mario Odyssey' to 'Super Mario Odyssey for Nintendo Switch'"
curl -X PUT \
  http://localhost:8080/pseg/products \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 9afc67c4-f122-431a-95bd-6c52d6e4fcf6' \
  -H 'cache-control: no-cache' \
  -d '{
	"id": 1,
	"description": "Super Mario Odyssey for Nintendo Switch",
	"price": 249.00
}'
echo ""
read -p "Press enter to continue"

echo ""

echo "Getting product"
curl -X GET \
  http://localhost:8080/pseg/products/1 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: e7c281ca-7ed3-4836-a50a-ef5d56623bb5' \
  -H 'cache-control: no-cache'
echo ""
read -p "Press enter to continue"

echo ""

echo "Creating 'John Appleseed' user"
curl -X POST \
  http://localhost:8080/pseg/users \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 4b6462ba-cabb-498b-a9e4-882d7c0f010d' \
  -H 'cache-control: no-cache' \
  -d '{
	"name": "John Appleseed",
	"cpf": 99999999999,
	"email": "johnnyblack@sao.com",
	"password": "Link Start"
}'
echo ""
read -p "Press enter to continue"

echo ""

echo "Renaming 'John Appleseed' to 'John Appleseed the Second'"
curl -X PUT \
  http://localhost:8080/pseg/users \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 12acd3c1-e381-4ce3-b667-7bd01bc8a475' \
  -H 'cache-control: no-cache' \
  -d '{
	"id": 1,
	"name": "John Appleseed the Second",
	"cpf": 99999999999,
	"email": "johnnyblack@sao.com",
	"password": "Link Start"
}'
echo ""
read -p "Press enter to continue"

echo ""

echo "Getting user"
curl -X GET \
  http://localhost:8080/pseg/users/1 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 4b08636d-9b24-4eb8-ad95-3a083615a18b' \
  -H 'cache-control: no-cache'
echo ""
read -p "Press enter to continue"

echo ""

echo "Creating a card"
curl -X POST \
  http://localhost:8080/pseg/cards \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 21041ee3-d488-4a0e-9c45-a1656df392eb' \
  -H 'cache-control: no-cache' \
  -d '{
	"number": 9999999999999999,
	"holderId": 1,
	"holder": "Johnny X Black",
	"cvv": 1234,
	"expiration": "2020-01-01"
}'
echo ""
read -p "Press enter to continue"

echo ""

echo "Editing the card"
curl -X PUT \
  http://localhost:8080/pseg/cards \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a1dc7011-839e-4a87-927e-928cc10f0d3e' \
  -H 'cache-control: no-cache' \
  -d '{
	"id": 1,
	"number": 9999999999999999,
	"holderId": 1,
	"holder": "Johnny Y Black",
	"cvv": 1234,
	"expiration": "2020-01-01"
}'
echo ""
read -p "Press enter to continue"

echo ""

echo "Getting the card"
curl -X GET \
  http://localhost:8080/pseg/cards/1 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 5190fb9f-3b1b-49d5-932e-6ead8aef42fb' \
  -H 'cache-control: no-cache'
echo ""
read -p "Press enter to continue"

echo ""

echo "Making transaction with card in wallet"
curl -X POST \
  http://localhost:8080/pseg/transactions \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 07c80bff-20b6-444d-bb1d-87796aa2a303' \
  -H 'cache-control: no-cache' \
  -d '{
	"userId": 1,
	"creditCardId": 1,
	"productId": 1
}'
echo ""
read -p "Press enter to continue"

echo ""

echo "Making transaction with unregistered card (Transaction goes well so card is saved)"
curl -X POST \
  http://localhost:8080/pseg/transactions \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 46d13614-1144-44fd-8d8b-d77cc6c67836' \
  -H 'cache-control: no-cache' \
  -d '{
	"userId": 1,
	"creditCardNumber": 22,
	"creditCardHolder": "John Bla Bla",
	"creditCardCvv": 123,
	"creditCardExpiration": "2020-12-12",
	"productId": 1
}'
echo ""
read -p "Press enter to continue"

echo ""

echo "Making transaction with unregistered card (Transaction is not OK so card is not saved)"
curl -X POST \
  http://localhost:8080/pseg/transactions \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: c101a7b8-00a2-41cd-beb4-eed046dc356d' \
  -H 'cache-control: no-cache' \
  -d '{
	"userId": 1,
	"creditCardNumber": 21,
	"creditCardHolder": "John Bla Bla",
	"creditCardCvv": 123,
	"creditCardExpiration": "2020-12-12",
	"productId": 1
}'
echo ""
read -p "Press enter to continue"

echo ""

echo "Getting the first transaction"
curl -X GET \
  http://localhost:8080/pseg/transactions/1 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 737e3f9c-78f5-4c2f-87ab-8815bd0390ca' \
  -H 'cache-control: no-cache'
echo ""
read -p "Press enter to continue"

echo ""

echo "Getting all user cards"
curl -X GET \
  http://localhost:8080/pseg/users/1/cards \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 0113862d-eb0d-49fb-899f-c4ac67397e38' \
  -H 'cache-control: no-cache'
echo ""
read -p "Press enter to continue"

echo ""

echo "Getting all user transactions"
curl -X GET \
  http://localhost:8080/pseg/users/1/transactions \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 76a922f8-4214-4d1d-86ba-5fb0ac1de372' \
  -H 'cache-control: no-cache'
echo ""
read -p "Press enter to continue"

echo ""

echo "Getting all paid transactions"
curl -X GET \
  'http://localhost:8080/pseg/users/1/transactions?paid=true' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 51ef7984-bac0-4964-8c88-1ca1e6c6e164' \
  -H 'cache-control: no-cache'
echo ""
read -p "Press enter to continue"

echo ""

echo "Getting all non-paid transactions"
curl -X GET \
  'http://localhost:8080/pseg/users/1/transactions?paid=false' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 0a81367b-dc1b-454e-9dc9-163faa434e19' \
  -H 'cache-control: no-cache'
echo ""
echo ""