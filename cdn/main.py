import time
import shutil
import os
from pathlib import Path
from fastapi import FastAPI, UploadFile, File, status
from fastapi.responses import FileResponse, JSONResponse
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

UPLOAD_DIR = Path("uploads")
UPLOAD_DIR.mkdir(exist_ok=True)

@app.get("/")
def alive():
    return {"message": "The server is alive"}
 
@app.post("/upload/")
async def upload(file: UploadFile = File(...)):
    filename = f"{int(time.time())}_{file.filename}"
    filepath = UPLOAD_DIR / filename
    with open(filepath, "wb") as f:
        shutil.copyfileobj(file.file, f)
    
    base_url = os.getenv("BASE_URL", "http://localhost:4000")
    return {"message": "uploaded", "url": f"{base_url}/images/{filename}"}

@app.get("/images/{filename}")
async def serve(filename: str):
    filepath = UPLOAD_DIR / filename
    if filepath.exists():
        return FileResponse(filepath)
    return JSONResponse(content={"error": "file not found"}, status_code=404)
