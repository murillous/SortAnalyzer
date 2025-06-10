# Análise de Performance de Algoritmos de Ordenação

Este projeto implementa e analisa a performance de três algoritmos de ordenação clássicos: **Selection Sort**, **Shell Sort** e **Heap Sort**. O sistema mede o tempo de execução e uso de memória para diferentes tamanhos de entrada e cenários de dados.

## 📋 Funcionalidades

- **Análise de Performance**: Medição precisa de tempo de execução e uso de memória
- **Múltiplos Cenários**: Teste em melhor caso, pior caso e caso médio
- **Diferentes Tamanhos**: Suporte a entradas de 100 até 10.000.000 elementos
- **Exportação de Dados**: Resultados salvos automaticamente em formato CSV
- **Configuração via Arquivo**: Entrada e algoritmo definidos através de arquivo de texto

## 🚀 Como Usar

### 1. Preparação do Arquivo de Entrada

Crie um arquivo `.txt` com duas linhas:
- **Linha 1**: Tamanho da entrada (100, 1000, 10000, 100000, 1000000, 10000000)
- **Linha 2**: Algoritmo de ordenação (selection sort, shell sort, heap sort)

**⚠️ IMPORTANTE - Formatação do Arquivo:**
- O arquivo deve estar codificado em **UTF-8 sem BOM (Byte Order Mark)**
- Utilize editores como Notepad++, VS Code ou similar que permitam salvar nesta codificação
- Evite usar o Bloco de Notas do Windows, que pode adicionar BOM automaticamente

**Exemplo de arquivo `config.txt`:**
```
10000
heap sort
```

### 2. Execução

Execute o arquivo JAR do projeto:

```bash
java -jar algoritmos-ordenacao.jar
```

O programa solicitará o caminho para o arquivo de configuração:
```
Digite o caminho para o arquivo .txt
-> config.txt
```

### 3. Resultados

Os resultados são salvos automaticamente no arquivo `resultado.csv` com as seguintes colunas:
- **Data**: Timestamp da execução
- **Algoritmo**: Nome do algoritmo utilizado
- **Entrada**: Tamanho do array
- **Caso**: Tipo de cenário (melhor caso, pior caso, caso médio)
- **Tempo(s)**: Tempo de execução em segundos
- **Memoria(MB)**: Uso de memória em megabytes

## 🔍 Algoritmos Implementados

### Selection Sort
- **Complexidade**: O(n²) para todos os casos
- **Melhor Caso**: Array já ordenado crescente
- **Pior Caso**: Array ordenado decrescente

### Shell Sort
- **Complexidade**: O(n log n) a O(n²) dependendo da sequência de gaps
- **Implementação**: Utiliza sequência de gaps baseada em (3^k - 1)/2
- **Melhor Caso**: Array já ordenado crescente
- **Pior Caso**: Array ordenado decrescente

### Heap Sort
- **Complexidade**: O(n log n) para todos os casos
- **Melhor Caso**: Array com todos elementos iguais
- **Pior Caso**: Array já ordenado crescente (devido à estrutura do heap)

## 📊 Cenários de Teste

### Melhor Caso
- **Selection/Shell Sort**: Array ordenado crescente [0, 1, 2, ..., n-1]
- **Heap Sort**: Array com todos elementos iguais [5, 5, 5, ..., 5]

### Pior Caso
- **Selection/Shell Sort**: Array ordenado decrescente [n-1, n-2, ..., 1, 0]
- **Heap Sort**: Array ordenado crescente [0, 1, 2, ..., n-1]

### Caso Médio
- Array com valores aleatórios entre 0 e 99.999
- Média de 5 execuções para maior precisão estatística

## 🛠️ Estrutura do Projeto

```
src/
├── Main.java                    # Classe principal
├── algoritmos/
│   └── Algoritmos.java         # Implementação dos algoritmos
└── utils/
    ├── AlgoritmoConstrutor.java # Leitura e validação do arquivo
    └── ResultadoCsv.java       # Geração do arquivo CSV
```

## 📈 Metodologia de Medição

### Tempo de Execução
- Uso de `System.nanoTime()` para máxima precisão
- Conversão para segundos com 6 casas decimais

### Uso de Memória
- Medição através de `Runtime.getRuntime()`
- Cálculo: `(totalMemory - freeMemory)` antes e depois da execução
- Múltiplas chamadas ao Garbage Collector para estabilizar medições

### Caso Médio
- 5 execuções com arrays aleatórios diferentes
- Cálculo da média aritmética dos resultados

## ⚠️ Considerações Importantes

- **Codificação do Arquivo**: O arquivo `.txt` deve estar em **UTF-8 sem BOM** para evitar erros de leitura
- **Garbage Collection**: O sistema força limpeza de memória antes de cada medição
- **Thread Sleep**: Pausas de 200ms para estabilização do sistema
- **Clonagem de Arrays**: Cada execução usa uma cópia independente dos dados
- **Validação de Entrada**: Verificação rigorosa dos parâmetros de entrada
- **Execução via JAR**: O programa deve ser executado através do arquivo JAR fornecido

## 📋 Requisitos

- Java 8 ou superior
- Memória RAM suficiente para arrays grandes (especialmente 10M elementos)
- Permissões de escrita para geração do arquivo CSV

## 🔧 Exemplo de Uso Completo

1. Crie o arquivo `teste.txt` **em UTF-8 sem BOM**:
```
1000000
shell sort
```

2. Execute o programa:
```bash
java -jar algoritmos-ordenacao.jar
Digite o caminho para o arquivo .txt
-> teste.txt
```

3. Verifique os resultados no arquivo `resultado.csv`:
```csv
Data,Algoritmo,Entrada,Caso,Tempo(s),Memoria(MB)
2024-06-10 14:30:25,shell sort,1000000,melhor caso,0.123456,15.234567
2024-06-10 14:30:26,shell sort,1000000,pior caso,0.234567,15.234567
2024-06-10 14:30:28,shell sort,1000000,caso medio,0.187432,15.456789
```

## 🎯 Objetivo Acadêmico

Este projeto foi desenvolvido para análise comparativa de algoritmos de ordenação, permitindo:
- Verificação empírica das complexidades teóricas
- Análise do comportamento prático dos algoritmos
- Comparação de performance entre diferentes implementações
- Estudo do impacto do tamanho da entrada na performance