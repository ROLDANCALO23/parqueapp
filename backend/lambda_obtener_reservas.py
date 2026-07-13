import json
import boto3
from decimal import Decimal

dynamodb = boto3.resource('dynamodb')
tabla = dynamodb.Table('Reservas')


def decimal_a_numero(obj):
    """DynamoDB devuelve números como Decimal; hay que convertirlos para json.dumps."""
    if isinstance(obj, Decimal):
        return float(obj) if obj % 1 != 0 else int(obj)
    raise TypeError


def lambda_handler(event, context):
    try:
        respuesta = tabla.scan()
        items = respuesta.get('Items', [])


        while 'LastEvaluatedKey' in respuesta:
            respuesta = tabla.scan(ExclusiveStartKey=respuesta['LastEvaluatedKey'])
            items.extend(respuesta.get('Items', []))


        items.sort(key=lambda r: (r.get('fecha', ''), r.get('hora', '')), reverse=True)

        return {
            'statusCode': 200,
            'headers': {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            'body': json.dumps(items, default=decimal_a_numero, ensure_ascii=False)
        }

    except Exception as e:
        return {
            'statusCode': 500,
            'headers': {'Content-Type': 'application/json'},
            'body': json.dumps({'error': str(e)}, ensure_ascii=False)
        }