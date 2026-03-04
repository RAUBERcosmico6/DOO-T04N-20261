MesEntrada_Cohort Q0 = 
VAR idCorretor = 'CORRETOR IBS'[id]
VAR primeiroMes =
    MINX(
        FILTER(
            ALL(FatoMensal_IBS),
            FatoMensal_IBS[vendcod] = idCorretor
        ),
        FatoMensal_IBS[Mes]
    )
RETURN
IF('CORRETOR IBS'[Ativo_>0_Historico] = 1, primeiroMes, BLANK())

-----------

