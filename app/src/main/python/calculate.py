def main(expression):
    try:
        answer = eval(expression)
    except:
        return "Error"
    else:
        return answer
