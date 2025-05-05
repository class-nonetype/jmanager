from sqlalchemy import create_engine, text
from src.config.utils import DATABASE_URL

# Crea el motor de base de datos utilizando SQLAlchemy.
# Este motor se encarga de gestionar la conexión y las interacciones con la base de datos.
engine = create_engine(
    url=DATABASE_URL                                       # URL de la base de datos configurada en el entorno.
    #connect_args={'options': '-c timezone={0}'.format(ZONE)}  # Configura la zona horaria para la conexión.
)

'''
with engine.connect() as connection:
    sql_statement = ''
    connection.execute(
        text(sql_statement)
    )
    connection.commit()
'''