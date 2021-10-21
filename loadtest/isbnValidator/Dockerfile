FROM python:3
WORKDIR /usr/src/app
COPY . .
RUN pip install --upgrade pip && \
    pip install requests
CMD ["LoadtestUtilJSW.py"]
ENTRYPOINT ["python3"]